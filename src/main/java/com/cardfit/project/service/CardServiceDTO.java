package com.cardfit.project.service;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.json.simple.JSONArray;

public class CardServiceDTO {
	
	private RestHighLevelClient client = null;
	private SearchRequest searchRequest = null;
	private SearchResponse searchResponse = null;
	private JSONArray result = null;
	private String filedname[] = null;
	
	public CardServiceDTO(RestHighLevelClient client, SearchRequest searchRequest, SearchResponse searchResponse,
			JSONArray result, String[] filedname) {
		super();
		this.client = client;
		this.searchRequest = searchRequest;
		this.searchResponse = searchResponse;
		this.result = result;
		this.filedname = filedname;
	}

	public RestHighLevelClient getClient() {
		return client;
	}

	public SearchRequest getSearchRequest() {
		return searchRequest;
	}

	public SearchResponse getSearchResponse() {
		return searchResponse;
	}

	public JSONArray getResult() {
		return result;
	}

	public String[] getFiledname() {
		return filedname;
	}
	
	public void setSearchResponse(SearchResponse searchResponse) {
		this.searchResponse = searchResponse;
	}

}
