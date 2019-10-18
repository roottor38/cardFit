package com.cardfit.project.service;

import java.io.IOException;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cardfit.project.config.ELConfiguration;

@Service
@Component
public class CardService {
	public CardService() {
		System.out.println("*** Start CardService ***");
	}

	@Autowired
	private ELConfiguration elConfig;

	// 내 카드검색
	public JSONArray cardNameSearch(String cardName, String queryTerm) {
		RestHighLevelClient client = elConfig.clientConnection();
		SearchRequest searchRequest = new SearchRequest();
		SearchResponse searchResponse = new SearchResponse();
		JSONArray result = new JSONArray();
		String query = "*" + queryTerm + "*";
		try {
			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
			searchSourceBuilder.query(QueryBuilders.wildcardQuery(cardName, query));
			searchRequest.source(searchSourceBuilder);
			searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
			client.close();
			SearchHit[] searchHits = searchResponse.getHits().getHits();

			for (SearchHit hit : searchHits) {
				result.add(hit.getSourceAsMap());
			}
		} catch (IOException e) {
			System.out.println("cardname search 발생된 예외 : " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	public JSONArray checkSearch(String[] keys) {
		RestHighLevelClient client = elConfig.clientConnection();
		JSONArray result = new JSONArray();
		SearchRequest searchRequest = new SearchRequest();
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		try {
			for (String key : keys) {
				searchSourceBuilder.query(QueryBuilders.wildcardQuery(key, "*"));
			}

			searchRequest.source(searchSourceBuilder);
			SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
			client.close();
			SearchHit[] searchHits = searchResponse.getHits().getHits();
			for (SearchHit hit : searchHits) {
				result.add(hit.getSourceAsMap());
			}
		} catch (IOException e) {
			System.out.println("check 발생된 예외 : " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	public JSONArray keywordSearch(String queryTerm) {
		RestHighLevelClient client = elConfig.clientConnection();
		JSONArray result = new JSONArray();
		SearchRequest searchRequest = new SearchRequest();
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		try {
			searchSourceBuilder.query(QueryBuilders.multiMatchQuery(queryTerm, "*"));
			searchRequest.source(searchSourceBuilder);
			SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
			client.close();
			SearchHit[] searchHits = searchResponse.getHits().getHits();

			for (SearchHit hit : searchHits) {
				result.add(hit.getSourceAsMap());
			}
		} catch (IOException e) {
			System.out.println("keyword 발생된 예외 : " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	public void deleteCard(String cardName, String queryTerm) {
		String ids = null;
		RestHighLevelClient client = elConfig.clientConnection();
		SearchRequest searchRequest = new SearchRequest();
		SearchResponse searchResponse = new SearchResponse();
		DeleteResponse deleteResponse = new DeleteResponse();
		try {
			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
			searchSourceBuilder.query(QueryBuilders.matchPhraseQuery(cardName, queryTerm));
			searchRequest.source(searchSourceBuilder);
			searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
			SearchHit[] searchHits = searchResponse.getHits().getHits();

			for (SearchHit hit : searchHits) {
				ids = hit.getId();
				DeleteRequest deleteRequest = new DeleteRequest("shinhan", "_doc", ids);
				deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);
			}
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("delete 발생된 예외 : " + e.getMessage());
		}
	}

	public void updateCard(String cardName, String queryTerm, String jsonString) {
		String id = null;
		RestHighLevelClient client = elConfig.clientConnection();
		SearchRequest searchRequest = new SearchRequest();
		SearchResponse searchResponse = new SearchResponse();
		UpdateResponse updateResponse = new UpdateResponse();
		try {
			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
			searchSourceBuilder.query(QueryBuilders.matchPhraseQuery(cardName, queryTerm));
			searchRequest.source(searchSourceBuilder);
			searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
			SearchHit[] searchHits = searchResponse.getHits().getHits();

			for (SearchHit hit : searchHits) {
				id = hit.getId();
				UpdateRequest updateRequest = new UpdateRequest("cardfit", "_doc", id); // index명 cardfit으로 바꿔줘야함
				updateRequest.doc(jsonString, XContentType.JSON);
				updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);
			}
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("update 발생된 예외 : " + e.getMessage());
		}
	}

	public boolean createCard(String bankname, String cardname, String condition, String movie, String cafe, String transportation, String telecom, String offshop, String onshop, String food, String others ) {
		RestHighLevelClient client = elConfig.clientConnection();
		boolean result = false;
		try {
            String json= 
            		"{"+
            			"\"bankname\" : "+ "\""+bankname+"\","+
            			"\"cardname\" : "+ "\""+cardname+"\","+
            			"\"condition\" : "+ "\""+condition+"\","+
            			"\"benefit\" : "+
            			"{"+
         					"\"movie\" : "+ "\""+movie+"\","+
         					"\"cafe\" : "+ "\""+cafe+"\","+
         					"\"transportation\" : "+ "\""+transportation+"\","+
         					"\"telecom\" : "+ "\""+telecom+"\","+
         					"\"offshop\" : "+ "\""+offshop+"\","+
         					"\"onshop\" : "+ "\""+onshop+"\","+
         					"\"food\" : "+ "\""+food+"\","+
         					"\"others\" : "+ "\""+others+"\""+
						"}"+
            		"}";
            IndexRequest request = new IndexRequest("cardfit","_doc").opType(DocWriteRequest.OpType.CREATE);;
            request.source(json, XContentType.JSON); 
            IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
            if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
            	result = true;
            } else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            	result = false;
            }
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("create 발생된 예외 : " + e.getMessage());
		}
		return result;
	}

}
