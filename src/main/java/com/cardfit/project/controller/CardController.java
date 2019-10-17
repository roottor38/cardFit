package com.cardfit.project.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cardfit.project.service.CardService;


@Controller
@Component
public class CardController {
	private static final Logger logger = LoggerFactory
			.getLogger(CardController.class);
	
	public CardController() {
		System.out.println("*** Start CardController ***");
	}
//	private ELConfiguration elConfig;
	
	@Autowired
	private CardService service;
	//-------------------- 서비스에서 수행되서온놈을 JSONArray로 바꿔서 프론트로 뿌려주는 놈-----------------
	
	// 구현 기능1 : 내 카드 검색
	@GetMapping("/searchMyCardBycardname")
	public JSONArray searchMyCardBycardname() throws IOException{
		return service.cardnameSearch("cardname", "요기요");
	}
	
	// 구현 기능2 : 카드 추천(체크박스)
	@GetMapping("/recommendCardByCheckBox")
	public JSONArray recommendCardByCheckBox(String[] terms) throws IOException {
		String[] test = {"benefit.cafe", "benefit.onshop"};
		JSONArray result = service.keywordSearch("이디야");
		System.out.println(result.getClass());
		return result;
	}
	
	@PostMapping("/cardControl")
	public String cardControl(@RequestParam(value = "bankname") String bankname,
			@RequestParam(value = "cardname") String cardname, Model model) {
		//관리자 카드 검색 로직( 위 내카드 혜택보기랑 같은 로직)
		JSONArray result = service.cardnameSearch("cardname", cardname);
		model.addAttribute("bankname",bankname);
		model.addAttribute("card", result);
		return "cardControl";
	}
	
	@RequestMapping("/myCardBenefit")
	public String myCardBenefit() {
		System.out.println("@RequestMapping");
		return "myCardBenefit";
	}

	@PostMapping("/myCardBenefit")
	public String myCardBenefit(@RequestParam(value = "bankname") String bankname, @RequestParam(value = "cardname") String cardname, Model model) throws IOException{
		if (cardname == null || cardname.length() == 0 || cardname.equals("")) {
			return "myCardBenefit";
		} else {
			JSONArray result = service.cardnameSearch("cardname", cardname);
			model.addAttribute("bankname",bankname);
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
			@RequestParam(value = "cardname") String cardname,
			@RequestParam(value = "condition") String condition,
			@RequestParam(value = "address") MultipartFile address,
			@RequestParam(value = "movie") String movie,
			@RequestParam(value = "cafe") String cafe,
			@RequestParam(value = "transportation") String transportation,
			@RequestParam(value = "telecom") String telecom,
			@RequestParam(value = "offshop") String offshop,
			@RequestParam(value = "onshop") String onshop,
			@RequestParam(value = "food") String food,
			@RequestParam(value = "others") String others) throws IOException {
		//관리자 카드 추가 로직
		String name = bankname+cardname;
		if (!address.isEmpty()) {
			try {
				BufferedImage bImageFromConvert = ImageIO.read(new ByteArrayInputStream(address.getBytes()));
				ImageIO.write(bImageFromConvert, "png",  new File("src\\main\\webapp\\images\\"+name+".jpg"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		JSONArray result = service.createCard(bankname, cardname, condition, movie, cafe, transportation, telecom, offshop, onshop, food, others);
		return "redirect:/admin.html";
	}
}