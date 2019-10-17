package com.cardfit.project.service;

import java.io.IOException;

import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
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
		System.out.println("---- Service started ----");
	}

	@Autowired
	private ELConfiguration elConfig;

	// 내 카드검색
	public JSONArray cardNameSearch(String fieldName, String queryTerm) {
		RestHighLevelClient client = elConfig.clientConnection();
		SearchRequest searchRequest = new SearchRequest();
		SearchResponse searchResponse = new SearchResponse();
		JSONArray result = new JSONArray();
		try {
			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
			searchSourceBuilder.query(QueryBuilders.matchPhraseQuery(fieldName, queryTerm));
			searchRequest.source(searchSourceBuilder);
			searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
			client.close();

			SearchHit[] searchHits = searchResponse.getHits().getHits();
			for (SearchHit hit : searchHits) {
				result.add(hit.getSourceAsMap());
			}
		} catch (IOException e) {
			System.out.println("발생된 예외 : " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	// 카드 추천(체크박스)
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
			System.out.println("발생된 예외 : " + e.getMessage());
			e.printStackTrace();
		}

		return result;
	}
	
	// 카드 추천(체크박스)
	public JSONArray keywordSearch(String keys) {
		RestHighLevelClient client = elConfig.clientConnection();
		JSONArray result = new JSONArray();
		SearchRequest searchRequest = new SearchRequest();
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		try {
			searchSourceBuilder.query(QueryBuilders.wildcardQuery("*", "이디야"));
			searchRequest.source(searchSourceBuilder);
			SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
			client.close();

			SearchHit[] searchHits = searchResponse.getHits().getHits();
			for (SearchHit hit : searchHits) {
				result.add(hit.getSourceAsMap());

			}
		} catch (IOException e) {
			System.out.println("발생된 예외 : " + e.getMessage());
			e.printStackTrace();
		}
		return result;
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
            IndexRequest request = new IndexRequest("shinhan","_doc").opType(DocWriteRequest.OpType.CREATE);;
            request.source(json, XContentType.JSON); 
            IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
            String index = indexResponse.getIndex();
            String id = indexResponse.getId();
            if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
            	result = true;
            } else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            	result = false;
                
            }
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
