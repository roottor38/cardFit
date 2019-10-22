package com.cardfit.project.service;

import java.io.IOException;

import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
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
	public int searchCountUp(String person, String id) {
		String field = null;
		RestHighLevelClient client = elConfig.clientConnection();
		if (person.equals("20m")) {
			field = "20m";
		} else if (person.equals("20f")) {
			field = "20f";
		} else if (person.equals("30m")) {
			field = "30m";
		} else if (person.equals("30f")) {
			field = "30f";
		} else if (person.equals("40m")) {
			field = "40m";
		} else if (person.equals("40f")) {
			field = "40f";
		}
		try {
			GetRequest getRequest = new GetRequest("countfit", id);
			GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
			int a = Integer.parseInt(getResponse.getSource().get(field).toString());
			a++;
			UpdateRequest request = new UpdateRequest("countfit", id).doc(field, a);
			client.update(request, RequestOptions.DEFAULT);
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("update 발생된 예외 : " + e.getMessage());
		}
		return 0;
	}

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
			System.out.println(searchResponse);
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
		String queryString = "*" + queryTerm + "*";
		try {
			// searchSourceBuilder.query(QueryBuilders.multiMatchQuery(queryTerm,
			// "benefit.*"));
			searchSourceBuilder.query(QueryBuilders.queryStringQuery(queryString).field("benefit.*"));
			searchRequest.source(searchSourceBuilder);
			System.out.println(searchRequest);
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

	public boolean deleteCard(String cardName, String queryTerm) {
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
				DeleteRequest deleteRequest = new DeleteRequest("cardfit", ids);
				deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);
			}
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("delete 발생된 예외 : " + e.getMessage());
			return false;
		}
		return true;
	}

	public boolean updateCard(String cardName, String bankname, String condition, String movie, String cafe,
			String transportation, String telecom, String offshop, String onshop, String food, String others) {
		String id = null;
		RestHighLevelClient client = elConfig.clientConnection();
		SearchRequest searchRequest = new SearchRequest();
		SearchResponse searchResponse = new SearchResponse();
		UpdateResponse updateResponse = new UpdateResponse();
		try {
			XContentBuilder builder = XContentFactory.jsonBuilder();
			builder.startObject();
			{
				builder.field("bankname", bankname).field("cardname", cardName).field("condition", condition)
						.field("movie", movie).field("cafe", cafe).field("transportation", transportation)
						.field("telecom", telecom).field("offshop", offshop).field("onshop", onshop).field("food", food)
						.field("others", others);
			}
			builder.endObject();

			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
			searchSourceBuilder.query(QueryBuilders.matchPhraseQuery("cardname", cardName));
			searchRequest.source(searchSourceBuilder);
			searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
			SearchHit[] searchHits = searchResponse.getHits().getHits();
			id = searchHits[0].getId();
			UpdateRequest updateRequest = new UpdateRequest("cardfit", id).doc(builder); // index명 cardfit으로
			// 바꿔줘야함
			updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);

			client.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("update 발생된 예외 : " + e.getMessage());
			return false;
		}
		return true;
	}

	public boolean createCard(String bankname, String cardname, String condition, String movie, String cafe,
			String transportation, String telecom, String offshop, String onshop, String food, String others) {
		RestHighLevelClient client = elConfig.clientConnection();
		boolean result = false;
		try {
			String json = "{" + "\"bankname\" : " + "\"" + bankname + "\"," + "\"cardname\" : " + "\"" + cardname
					+ "\"," + "\"condition\" : " + "\"" + condition + "\"," + "\"benefit\" : " + "{" + "\"movie\" : "
					+ "\"" + movie + "\"," + "\"cafe\" : " + "\"" + cafe + "\"," + "\"transportation\" : " + "\""
					+ transportation + "\"," + "\"telecom\" : " + "\"" + telecom + "\"," + "\"offshop\" : " + "\""
					+ offshop + "\"," + "\"onshop\" : " + "\"" + onshop + "\"," + "\"food\" : " + "\"" + food + "\","
					+ "\"others\" : " + "\"" + others + "\"" + "}" + "}";
			IndexRequest request = new IndexRequest("cardfit");
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