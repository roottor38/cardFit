package com.cardfit.project.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cardfit.project.service.CardDBService;
import com.cardfit.project.service.CardService;

import model.dto.Count;
import model.dto.Customer;
import model.dto.Summation;

@Controller
@Component
public class CardController {
	
	public CardController() {
		System.out.println("*** Start CardController ***");
	}
//	private ELConfiguration elConfig;
	
	@Autowired
	private CardService service;
	//-------------------- 서비스에서 수행되서온놈을 JSONArray로 바꿔서 프론트로 뿌려주는 놈-----------------
	@RequestMapping("/test")
	public JSONArray test() {
		return service.descCard();
	}
	
	
	
	
	@RequestMapping("/option")
	public String option() {
		return "option";
	}
	
	@PostMapping("/option")
	public String option(@RequestParam(value="movie", required=false) String movie, @RequestParam(value="telecom", required=false) String telecom, @RequestParam(value="transportation", required=false) String transportation,
			@RequestParam(value="offshop", required=false) String offshop, @RequestParam(value="onshop", required=false) String onshop, @RequestParam(value="food", required=false) String food,
			@RequestParam(value="cafe", required=false) String cafe, @RequestParam(value="others", required=false) String others, Model model, HttpSession session) {
		//체크 박스로 추천 로직
		ArrayList<String> data = new ArrayList<>();
		ArrayList<String> DBdata = new ArrayList<>();
		if(movie!=null) {
			data.add("benefit.movie");
			DBdata.add("movie");
		}
		if(telecom!=null) {
			data.add("benefit.telecom");
			DBdata.add("telecom");
		}
		if(transportation!=null) {
			data.add("benefit.transportation");
			DBdata.add("transportation");
		}
		if(offshop!=null) {
			data.add("benefit.offshop");
			DBdata.add("offshop");
		}
		if(onshop!=null) {
			data.add("benefit.onshop");
			DBdata.add("onshop");
		}
		if(food!=null) {
			data.add("benefit.food");
			DBdata.add("food");
		}
		if(cafe!=null) {
			data.add("benefit.cafe");
			DBdata.add("cafe");
		}
		if(others!=null) {
			data.add("benefit.others");
			DBdata.add("others");
		}
		String[] DBterm = new String[DBdata.size()];
		String[] term = new String[data.size()];
		String name = ((Customer)session.getAttribute("customer")).getAge()+((Customer)session.getAttribute("customer")).getGender();
		for(int i = 0 ; i < DBdata.size(); i++) {
			DBterm[i] = DBdata.get(i);
		}
		for(int i = 0; i < data.size(); i++) {
			term[i] = data.get(i);
		}
		
		try {
			boolean countResult = CardDBService.updateCount(DBterm,name);
			JSONArray result = service.checkSearch(term, name);
			model.addAttribute("card", result);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return "option";
	}
	
	@RequestMapping("/keyword")
	public String keyword() {
		return "keyword";
	}
	
	@PostMapping("/keyword")
	public String keyword(@RequestParam(value = "search") String search, Model model, HttpSession session) {
		if (search == null || search.equals("") || search.length() == 0) {
			return "keyword";
		} else {
			System.out.println(session.getAttribute("customer"));
			String user = ((Customer)session.getAttribute("customer")).getAge()+((Customer)session.getAttribute("customer")).getGender();
			JSONArray result = service.keywordSearch(user, search);
			model.addAttribute("card", result);
		}
		return "keyword";
	}
	
	@PostMapping("/index")
	public String index(@RequestParam(value = "gender") String gender,
		@RequestParam(value = "age") String age, HttpSession session) {
		Customer customer = new Customer(gender, Long.valueOf(age));
		session.setAttribute("customer", customer);
		return "index";
	}
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	
	@PostMapping("/cardControl")
	public String cardControl(@RequestParam(value = "cardname") String cardname, Model model) {
		JSONArray result = service.cardNameSearch(cardname);
		model.addAttribute("card", result);
		return "cardControl";
	}
	
	@RequestMapping("/myCardBenefit")
	public String myCardBenefit() {
		return "myCardBenefit";
	}
	
	
	@RequestMapping("/histogram")
	public ModelAndView histogram() {
		ModelAndView view = new ModelAndView();
		Summation data =  null;
		try {
			data = CardDBService.getSummation();
		}catch(SQLException e) {
			e.printStackTrace();
		}
        RConnection connection = null;
        String[] name = {"영화","카페","핸드폰","대중교통","쇼핑","온라인쇼핑","외식","기타"};
        String value = "value <- c("  +data.getMovie()+","
        		+ data.getCafe() +","
        		+ data.getTelecom()+","
        		+ data.getTransportation()+","
        		+ data.getOnshop()+","
        		+ data.getOffshop()+","
        		+ data.getFood()+","
        		+ data.getOthers() +")";
        String df = "data<-data.frame('name','value')";
        try {
            connection = new RConnection();
            connection.eval(value);
            connection.assign("name",name);
            connection.eval(df);
            System.out.println(0);
            connection.eval("library(ggplot2)");
            System.out.println(1);
            connection.eval("jpeg(\"histogram.jpg\")");
            System.out.println(2);
            connection.eval("ggplot(data,aes(name,value))+geom_bar(stat=\"identity\",fill=\"blue\")");
            System.out.println(3);
            connection.eval("dev.off()");
            System.out.println(4);
            connection.assign("aa","한글");
            System.out.println(5);
            connection.close();

            view.addObject("viewPage", "histogram.jsp");
            
        } catch (Exception e) {
        	e.printStackTrace();
        }
		return view;
	}
	
	@RequestMapping("/insight")
	public String insight(Model model) {
		ArrayList<Count> data= new ArrayList<>();
		try {
			data = CardDBService.getAllCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			model.addAttribute("data",data);
			System.out.println(data);
		return "insight";
	}

	@PostMapping("/myCardBenefit")
	public String myCardBenefit(@RequestParam(value = "cardname") String cardname, Model model, HttpSession session) throws IOException{
		if (cardname == null || cardname.length() == 0 || cardname.equals("")) {
			return "myCardBenefit";
		} else {
			String user = ((Customer)session.getAttribute("customer")).getAge()+((Customer)session.getAttribute("customer")).getGender();
			model.addAttribute("card", service.cardNameSearch(cardname, user));
			return "myCardBenefit";
		}
	}
	
	@RequestMapping("/createCard")
	public String createCard() {
		return "createCard";
	}
	
	@PostMapping("/createCard")
	public String createCard(@RequestParam(value = "bankname") String bankname,
			@RequestParam(value = "cardname", required=false) String cardname,
			@RequestParam(value = "condition", required=false) String condition,
			@RequestParam(value = "address", required=false) MultipartFile address,
			@RequestParam(value = "movie", required=false) String movie,
			@RequestParam(value = "cafe", required=false) String cafe,
			@RequestParam(value = "transportation", required=false) String transportation,
			@RequestParam(value = "telecom", required=false) String telecom,
			@RequestParam(value = "offshop", required=false) String offshop,
			@RequestParam(value = "onshop", required=false) String onshop,
			@RequestParam(value = "food", required=false) String food,
			@RequestParam(value = "others", required=false) String others) throws IOException {
		//관리자 카드 추가 로직
		if (!address.isEmpty()) {
			try {
				BufferedImage bImageFromConvert = ImageIO.read(new ByteArrayInputStream(address.getBytes()));
				ImageIO.write(bImageFromConvert, "png",  new File("src\\main\\webapp\\images\\"+bankname+cardname+".png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(condition.length()==0) {
			condition=null;
		}
		if(movie.length()==0) {
			movie=null;
		}
		if(cafe.length()==0) {
			cafe=null;
		}
		if(transportation.length()==0) {
			transportation=null;
		}
		if(telecom.length()==0) {
			telecom=null;
		}
		if(offshop.length()==0) {
			offshop=null;
		}
		if(onshop.length()==0) {
			onshop=null;
		}
		if(food.length()==0) {
			food=null;
		}
		if(others.length()==0) {
			others=null;
		}
		boolean result = service.createCard(bankname, cardname, condition, movie, cafe, transportation, telecom, offshop, onshop, food, others);
		
		return "redirect:/admin.html";
	}
	
	@RequestMapping("/updateCard/{cardname}")
	public String updateCard(@PathVariable(value = "cardname") String cardname, Model model) {
		//카드 수정을 위한 은행명하고 카드명으로 값 검색 로직
		JSONArray result = service.cardNameSearch(cardname);
		model.addAttribute("card", result);
		return "updateCard";
	}
	
	@PostMapping("/updateCard")
	public String updateCard(@RequestParam(value = "bankname", required=false) String bankname,
			@RequestParam(value = "cardname", required=false) String cardname,
			@RequestParam(value = "condition", required=false) String condition,
			@RequestParam(value = "movie", required=false) String movie,
			@RequestParam(value = "cafe", required=false) String cafe,
			@RequestParam(value = "transportation", required=false) String transportation,
			@RequestParam(value = "telecom", required=false) String telecom,
			@RequestParam(value = "offshop", required=false) String offshop,
			@RequestParam(value = "onshop", required=false) String onshop,
			@RequestParam(value = "food", required=false) String food,
			@RequestParam(value = "others", required=false) String others) {
		if(condition.length()==0) {
			condition=null;
		}
		if(movie.length()==0) {
			movie=null;
		}
		if(cafe.length()==0) {
			cafe=null;
		}
		if(transportation.length()==0) {
			transportation=null;
		}
		if(telecom.length()==0) {
			telecom=null;
		}
		if(offshop.length()==0) {
			offshop=null;
		}
		if(onshop.length()==0) {
			onshop=null;
		}
		if(food.length()==0) {
			food=null;
		}
		if(others.length()==0) {
			others=null;
		}
		service.updateCard(cardname, bankname, condition, movie, cafe, transportation, telecom, offshop, onshop, food, others);
		return "redirect:/admin.html";
	}
	
	

}