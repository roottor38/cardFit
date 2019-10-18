package com.cardfit.project.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cardfit.project.service.CardService;


import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@Component
public class CardController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(CardController.class);
	
	public CardController() {
		System.out.println("*** Start CardController ***");
	}

	@Autowired
	private CardService service;

	@GetMapping("/searchMyCardByCardName")
	public JSONArray searchMyCardByCardName(String cardName, String queryTerm) throws IOException {
		JSONArray result = service.cardNameSearch(cardName, queryTerm);
		return result;
	}

	@GetMapping("/recommendCardByCheckBox")
	public JSONArray recommendCardByCheckBox(String[] queryTerms) throws IOException {
		String[] queryTerms2 = {"benefit.movie", "benefit.cafe"};
		JSONArray result = service.checkSearch(queryTerms2);
		return result;
	}
	@RequestMapping("/option")
	public String option() {
		return "option";
	}

	@PostMapping("/option")
	public String option(@RequestParam(value="movie", required=false) String movie, @RequestParam(value="telecome", required=false) String telecome, @RequestParam(value="traffic", required=false) String traffic,
			@RequestParam(value="offshop", required=false) String offshop, @RequestParam(value="onshop", required=false) String onshop, @RequestParam(value="food", required=false) String food,
			@RequestParam(value="cafe", required=false) String cafe, @RequestParam(value="others", required=false) String others, Model model) {
		//체크 박스로 추천 로직
		return "option";
	}
	@GetMapping("/searchCardByKeyword")
	public JSONArray searchCardByKeyword(String queryTerm) throws IOException {
		JSONArray result = service.keywordSearch(queryTerm);
		return result;
	}

	@DeleteMapping("/deleteCard")
	public void deleteCard(String cardName, String queryTerm) throws IOException {
		service.deleteCard("cardName", "queryTerm");
		System.out.println("**** 실행 결과 : 카드 삭제 완료");
	}

	@PostMapping("/updateCard")
	public void updateCard(String cardName, String queryTerm, JSONObject input) throws IOException {
		String data = input.toJSONString();
		service.updateCard(cardName, queryTerm, data);
		System.out.println("**** 실행 결과 : 카드 업데이트 완료");
	}

	@PostMapping("/cardControl")
	public String cardControl(@RequestParam(value = "bankname") String bankName, @RequestParam(value = "cardname") String cardName, Model model) {
		JSONArray result = service.cardNameSearch("cardname", cardName);
		model.addAttribute("bankname", bankName);
		model.addAttribute("card", result);
		return "cardControl";
	}
	
	@PostMapping("/myCardBenefit")
	public String myCardBenefit(@RequestParam(value = "bankname") String bankname, @RequestParam(value = "cardname") String cardname, Model model) throws IOException{
		if (cardname == null || cardname.length() == 0 || cardname.equals("")) {
			return "myCardBenefit";
		} else {
			JSONArray result = service.cardNameSearch("cardname", cardname);
			model.addAttribute("bankname", bankname);
			model.addAttribute("card", result);
			System.out.println(result);
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
		String name = bankname+cardname;
		if (!address.isEmpty()) {
			try {
				BufferedImage bImageFromConvert = ImageIO.read(new ByteArrayInputStream(address.getBytes()));
				ImageIO.write(bImageFromConvert, "png",  new File("src\\main\\webapp\\images\\"+name+".jpg"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		boolean result = service.createCard(bankname, cardname, condition, movie, cafe, transportation, telecom, offshop, onshop, food, others);
		return "redirect:/admin.html";
	}
}
