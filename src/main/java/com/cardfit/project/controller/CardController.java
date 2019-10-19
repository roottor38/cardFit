package com.cardfit.project.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cardfit.project.service.CardDBService;
import com.cardfit.project.service.CardService;

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
	
	@RequestMapping("/option")
	public String option() {
		return "option";
	}
	
	@PostMapping("/option")
	public String option(@RequestParam(value="movie", required=false) String movie, @RequestParam(value="telecom", required=false) String telecom, @RequestParam(value="transportation", required=false) String transportation,
			@RequestParam(value="offshop", required=false) String offshop, @RequestParam(value="onshop", required=false) String onshop, @RequestParam(value="food", required=false) String food,
			@RequestParam(value="cafe", required=false) String cafe, @RequestParam(value="others", required=false) String others, Model model) {
		//체크 박스로 추천 로직
		ArrayList<String> data = new ArrayList<>();
		if(movie!=null) {
			data.add("benefit.movie");
		}
		if(telecom!=null) {
			data.add("benefit.telecom");
		}
		if(transportation!=null) {
			data.add("benefit.transportation");
		}
		if(offshop!=null) {
			data.add("benefit.offshop");
		}
		if(onshop!=null) {
			data.add("benefit.onshop");
		}
		if(food!=null) {
			data.add("benefit.food");
		}
		if(cafe!=null) {
			data.add("benefit.cafe");
		}
		if(others!=null) {
			data.add("benefit.others");
		}
		String[] term = new String[data.size()];
		for(int i = 0; i < data.size(); i++) {
			term[i] = data.get(i);
		}
		JSONArray result = service.checkSearch(term);
		model.addAttribute("card", result);
		return "option";
	}
	
	@RequestMapping("/keyword")
	public String keyword() {
		return "keyword";
	}
	
	@PostMapping("/keyword")
	public String keyword(@RequestParam(value = "search") String search, Model model) {
		if (search == null || search.equals("") || search.length() == 0) {
			return "keyword";
		} else {
			JSONArray result = service.keywordSearch(search);
			model.addAttribute("card", result);
		}
		return "keyword";
	}
	
	@PostMapping("/cardControl")
	public String cardControl(@RequestParam(value = "cardname") String cardname, Model model) {
		JSONArray result = service.cardNameSearch("cardname", cardname);
		model.addAttribute("card", result);
		return "cardControl";
	}
	
	@RequestMapping("/myCardBenefit")
	public String myCardBenefit() {
		return "myCardBenefit";
	}

	@PostMapping("/myCardBenefit")
	public String myCardBenefit(@RequestParam(value = "cardname") String cardname, Model model) throws IOException{
		if (cardname == null || cardname.length() == 0 || cardname.equals("")) {
			return "myCardBenefit";
		} else {
			JSONArray result = service.cardNameSearch("cardname", cardname);
			model.addAttribute("card", result);
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
				ImageIO.write(bImageFromConvert, "png",  new File("src\\main\\webapp\\images\\"+bankname+cardname+".jpg"));
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
		JSONArray result = service.cardNameSearch("cardname", cardname);
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
		boolean result = service.updateCard("cardname", cardname, bankname, condition, movie, cafe, transportation, telecom, offshop, onshop, food, others);
		return "redirect:/admin.html";
	}
	
	

}