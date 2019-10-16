package com.cardfit.project.controller;

import java.io.IOException;

import org.elasticsearch.search.SearchHits;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cardfit.project.service.CardService;


@RestController
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
	public SearchHits searchMyCardByCardName() throws IOException{
		JSONArray result = new JSONArray();
		System.out.println("--시작");
		SearchHits hitsResult = service.cardNameSearch("cardname", "요기요");
		System.out.println("--끝");
		//result = (JSONArray)hitsResult;
		return hitsResult;
	}
	
	// 구현 기능2 : 카드 추천(체크박스)
	@GetMapping("/recommendCardByCheckBox")
	public JSONArray recommendCardByCheckBox(String[] terms) {
		JSONArray result = new JSONArray();
		return result;
	}
	
//	@GetMapping("/searchAllAboutBankByIndex")
//	public Object searchAllAboutBankByIndex(@RequestParam String index, @RequestParam String type, @RequestParam String id ) throws IOException {
//		GetRequest request = new GetRequest("shinhan","_doc","1");
//		GetResponse response = null;
//		try(RestHighLevelClient client = clientConnection())
//		{
//			response = client.get(request, RequestOptions.DEFAULT);
//			client.close();
//		}catch (ElasticsearchException e) {
//			if(e.status() == RestStatus.NOT_FOUND) {
//				System.out.println("client 연결 오류");
//			}
//		}
//		return response.isExists() ? response : null;
//	}
//	
//	//index, cardname(카드명)으로 카드 검색하기
//	//@GetMapping("/cardInfo")
//	public JSONObject getCardfromIndex(String cardName) {
//		JSONObject info = null;
//		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//		searchSourceBuilder.query(QueryBuilders.termQuery("cardname", cardName));
//		return info;
//	}
//	
//	//multi Query
//	@PostMapping("/multiQ")
//	@ResponseStatus(HttpStatus.OK)
//	public SearchResponse getAllMatchedInfo() {
//		System.out.println("getAllMatchedInfo 시작");
//		
//	}
	
	
}