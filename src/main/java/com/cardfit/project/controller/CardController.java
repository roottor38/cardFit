package com.cardfit.project.controller;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cardfit.project.service.CardService;

@RestController
@Component
public class CardController {
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
		JSONArray result = service.checkSearch(queryTerms);
		return result;
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

}
