package com.cardfit.project.controller;

import java.io.IOException;

import org.elasticsearch.search.SearchHits;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	
	// 구현 기능1 : 내 카드 검색
	@GetMapping("/searchMyCardByCardName")
	public JSONArray searchMyCardByCardName() throws IOException{
		return service.cardNameSearch("cardname", "요기요");
	}
	
	// 구현 기능2 : 카드 추천(체크박스)
	@GetMapping("/recommendCardByCheckBox")
	public JSONArray recommendCardByCheckBox(String[] terms) throws IOException {
		String[] test = {"benefit.cafe", "benefit.onshop"};
		JSONArray result = service.keywordSearch("이디야");
		System.out.println(result.getClass());
		return result;
	}
	
	@RequestMapping("/myCardBenefit")
	public String myCardBenefit() {
		System.out.println("@RequestMapping");
		return "myCardBenefit";
	}

	@PostMapping("/myCardBenefit")
	public String myCardBenefit(@RequestParam(value = "bankName") String bankName, @RequestParam(value = "cardName") String cardName, Model model) throws IOException{
		if (cardName == null || cardName.length() == 0 || cardName.equals("")) {
			return "myCardBenefit";
		} else {
			System.out.println(bankName + "      " + cardName);
			JSONArray result = service.cardNameSearch("cardname", cardName);
			model.addAttribute("card", result);
			return "myCardBenefit";
		}
	}
	
	
}