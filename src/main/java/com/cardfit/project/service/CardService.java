package com.cardfit.project.service;

import java.io.IOException;

import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cardfit.project.config.ELConfiguration;

@Service
@Component
public class CardService {

	//모든카드 연령대 count 내림차순
	public JSONArray descCard() {

		CardServiceDTO cardServiceDTO = init();
		calc(cardServiceDTO);

//		RestHighLevelClient client = elConfig.clientConnection();
//		SearchRequest searchRequest = new SearchRequest("cardfit");
//		SearchResponse searchResponse = new SearchResponse();
//		JSONArray result = new JSONArray();
//		String filedname[] = {"20m", "20f", "30m", "30f", "40m", "40f"};

		

	}   


	public CardServiceDTO init() {
		RestHighLevelClient client = elConfig.clientConnection();
		SearchRequest searchRequest = new SearchRequest("cardfit");
		SearchResponse searchResponse = new SearchResponse();
		JSONArray result = new JSONArray();
		String filedname[] = {"20m", "20f", "30m", "30f", "40m", "40f"};
		CardServiceDTO cardServiceDTO = new CardServiceDTO(client, searchRequest, searchResponse, result, filedname);
		
		return cardServiceDTO;
	}
	
	public JSONArray calc(CardServiceDTO cardServiceDTO) {
		try {
			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
			for(String filed : cardServiceDTO.getFiledname()) {
				searchSourceBuilder.query(QueryBuilders.matchAllQuery());
				searchSourceBuilder.size(1);
				cardServiceDTO.getSearchRequest().source(searchSourceBuilder.sort(new FieldSortBuilder(filed).order(SortOrder.DESC)));
				System.out.println(searchSourceBuilder);
				cardServiceDTO.setSearchResponse(cardServiceDTO.getClient().search(cardServiceDTO.getSearchRequest(), RequestOptions.DEFAULT));
				cardServiceDTO.getResult().add(cardServiceDTO.getSearchResponse().getHits().getHits()[0]);
				searchSourceBuilder = new SearchSourceBuilder();
			}
			cardServiceDTO.getClient().close();
		} catch (IOException e) {
			System.out.println("발생된 예외 : " + e.getMessage());
			e.printStackTrace();
		}
		return cardServiceDTO.getResult();
	}
	
	@Autowired
	private ELConfiguration elConfig;

	// 내 카드검색
	public void searchCountUp(String user, String id) {
		RestHighLevelClient client = elConfig.clientConnection();

		try {
			GetRequest getRequest = new GetRequest("cardfit", id);
			GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
			int a = Integer.parseInt(getResponse.getSource().get(user).toString());
			a++;
			UpdateRequest request = new UpdateRequest("cardfit", id).doc(user, a);
			client.update(request, RequestOptions.DEFAULT);
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("update 발생된 예외 : " + e.getMessage());
		}
	}

	public JSONArray cardNameSearch(String queryTerm, String user) {
		RestHighLevelClient client = elConfig.clientConnection();
		SearchRequest searchRequest = new SearchRequest();
		SearchResponse searchResponse = new SearchResponse();
		JSONArray result = new JSONArray();
		try {
			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
			searchSourceBuilder.query(QueryBuilders.matchPhrasePrefixQuery("cardname", queryTerm));
			searchSourceBuilder.size(100);
			searchRequest.source(searchSourceBuilder);
			searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
			SearchHit[] searchHits = searchResponse.getHits().getHits();
			for (SearchHit hit : searchHits) {
				searchCountUp(user, hit.getId());
				result.add(hit.getSourceAsMap());

			}
			client.close();
		} catch (IOException e) {
			System.out.println("cardname search 발생된 예외 : " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	public JSONArray cardNameSearch(String queryTerm) {
		RestHighLevelClient client = elConfig.clientConnection();
		SearchRequest searchRequest = new SearchRequest();
		SearchResponse searchResponse = new SearchResponse();
		JSONArray result = new JSONArray();
		try {
			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
			searchSourceBuilder.query(QueryBuilders.matchPhrasePrefixQuery("cardname", queryTerm));
			searchSourceBuilder.size(100);
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

	public JSONArray checkSearch(String[] keys, String user) {
		RestHighLevelClient client = elConfig.clientConnection();
		JSONArray result = new JSONArray();
		SearchRequest searchRequest = new SearchRequest();
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		try {
			for (String key : keys) {
				searchSourceBuilder.query(QueryBuilders.wildcardQuery(key, "*"));
			}

			searchRequest.source(searchSourceBuilder.size(100));
			SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
			client.close();
			SearchHit[] searchHits = searchResponse.getHits().getHits();
			for (SearchHit hit : searchHits) {
				searchCountUp(user, hit.getId());
				result.add(hit.getSourceAsMap());
			}
		} catch (IOException e) {
			System.out.println("check 발생된 예외 : " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	public JSONArray keywordSearch(String user, String queryTerm) {
		RestHighLevelClient client = elConfig.clientConnection();
		JSONArray result = new JSONArray();
		SearchRequest searchRequest = new SearchRequest();
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		String queryString = "*" + queryTerm + "*";
		try {
			searchSourceBuilder.query(QueryBuilders.queryStringQuery(queryString).field("benefit.*"));
			searchRequest.source(searchSourceBuilder.size(100));
			SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
			client.close();
			SearchHit[] searchHits = searchResponse.getHits().getHits();

			for (SearchHit hit : searchHits) {
				searchCountUp(user, hit.getId());
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
		try {
			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
			searchSourceBuilder.query(QueryBuilders.matchPhraseQuery(cardName, queryTerm));
			searchRequest.source(searchSourceBuilder);
			searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
			SearchHit[] searchHits = searchResponse.getHits().getHits();

			for (SearchHit hit : searchHits) {
				ids = hit.getId();
				DeleteRequest deleteRequest = new DeleteRequest("cardfit", ids);
				client.delete(deleteRequest, RequestOptions.DEFAULT);
			}
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("delete 발생된 예외 : " + e.getMessage());
			return false;
		}
		return true;
	}

	public void updateCard(String cardName, String bankname, String condition, String movie, String cafe,
			String transportation, String telecom, String offshop, String onshop, String food, String others) {
		String id = null;
		RestHighLevelClient client = elConfig.clientConnection();
		SearchRequest searchRequest = new SearchRequest();
		SearchResponse searchResponse = new SearchResponse();
		try {
			XContentBuilder builder = XContentFactory.jsonBuilder();
			builder.startObject();
			{
				builder.field("bankname", bankname).field("cardname", cardName).field("condition", condition);
			}
			builder.startObject("benefit").field("movie", movie).field("cafe", cafe).field("transportation", transportation)
			.field("telecom", telecom).field("offshop", offshop).field("onshop", onshop).field("food", food)
			.field("others", others).endObject();
			builder.endObject();

			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
			searchSourceBuilder.query(QueryBuilders.matchPhraseQuery("cardname", cardName));
			searchRequest.source(searchSourceBuilder);
			searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
			SearchHit[] searchHits = searchResponse.getHits().getHits();
			id = searchHits[0].getId();
			UpdateRequest updateRequest = new UpdateRequest("cardfit", id).doc(builder); // index명 cardfit으로
			client.update(updateRequest, RequestOptions.DEFAULT);

			client.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("update 발생된 예외 : " + e.getMessage());
		}
	}

	public boolean createCard(String bankname, String cardname, String condition, String movie, String cafe,
			String transportation, String telecom, String offshop, String onshop, String food, String others) {
		RestHighLevelClient client = elConfig.clientConnection();
		boolean result = false;
		try {

			XContentBuilder builder = XContentFactory.jsonBuilder();
			builder.startObject();
			{
				builder.field("bankname", bankname).field("cardname", cardname).field("condition", condition)
				.field("20m", 0).field("20f", 0).field("30m", 0).field("30f", 0).field("40m", 0).field("40f", 0);
			}
			builder.startObject("benefit").field("movie", movie).field("cafe", cafe).field("transportation", transportation)
			.field("telecom", telecom).field("offshop", offshop).field("onshop", onshop).field("food", food)
			.field("others", others).endObject();
			builder.endObject();
			IndexRequest request = new IndexRequest("cardfit").source(builder);
			IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
			if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
				result = true;
			} else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
				result = false;
			}
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("create 발생된 예외 : " + e.getMessage());
		}
		return result;
	}

}