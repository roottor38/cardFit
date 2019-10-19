package com.cardfit.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cardfit.project.service.CardService;

@CrossOrigin
@RestController
@Component
public class CardRestController {

	public CardRestController() {
		System.out.println("*** Start CardRestController ***");
	}
	
	@Autowired
	private CardService service;
	
	@DeleteMapping("deleteCard/{cardname}")
	public void deleteCard(@PathVariable String cardname) {
		System.out.println(cardname);
		service.deleteCard("cardname", cardname);
	}
	

	
}
