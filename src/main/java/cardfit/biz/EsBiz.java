package cardfit.biz;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.script.mustache.MultiSearchTemplateRequest;
import org.elasticsearch.script.mustache.MultiSearchTemplateResponse;
import org.elasticsearch.script.mustache.MultiSearchTemplateResponse.Item;
import org.elasticsearch.script.mustache.SearchTemplateRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class EsBiz {
	public static RestHighLevelClient createConnection() {
        return new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.22.55", 9200, "http")));
    }
	
	public static SearchHits cardNameSearch() throws IOException {
		RestHighLevelClient client = createConnection();
		SearchRequest searchRequest = new SearchRequest(); 
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); 
		searchSourceBuilder.query(QueryBuilders.matchPhraseQuery("benefit", "이디야"));
		searchRequest.source(searchSourceBuilder);
		SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		client.close();
		SearchHit[] searchHits = searchResponse.getHits().getHits();
		for (SearchHit hit : searchHits) {
			System.out.println(hit.getSourceAsString());
			System.out.println(hit.getId());
			
		}
		return searchResponse.getHits();
	}
	
	public static SearchHits checkSearch() throws IOException {
		RestHighLevelClient client = createConnection();
		SearchRequest searchRequest = new SearchRequest(); 
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); 
		searchSourceBuilder.query(QueryBuilders.wildcardQuery("benefit.cafe", "*"));
		searchSourceBuilder.query(QueryBuilders.wildcardQuery("benefit.onshop", "*"));
		searchRequest.source(searchSourceBuilder);
		SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		client.close();
		SearchHit[] searchHits = searchResponse.getHits().getHits();
		for (SearchHit hit : searchHits) {
			System.out.println(hit.getSourceAsString());
			System.out.println(hit.getId());
			
		}
		return searchResponse.getHits();
	}
	
	public static Item[] multiSearch() throws IOException {
		RestHighLevelClient client = createConnection();
		String [] searchTerms = {"더본", "요기요", "해피포인트"};

		MultiSearchTemplateRequest multiRequest = new MultiSearchTemplateRequest(); 
		for (String searchTerm : searchTerms) {
		    SearchTemplateRequest request = new SearchTemplateRequest();  
		    request.setRequest(new SearchRequest("shinhan"));

		    request.setScriptType(ScriptType.INLINE);
		    request.setScript(
		        "{" +
		        "  \"query\": { \"match\" : { \"{{field}}\" : \"{{value}}\" } }," +
		        "  \"size\" : \"{{size}}\"" +
		        "}");

		    Map<String, Object> scriptParams = new HashMap<>();
		    scriptParams.put("field", "benefit");
		    scriptParams.put("value", searchTerm);
		    scriptParams.put("size", 5);
		    request.setScriptParams(scriptParams);

		    multiRequest.add(request);
		}
		MultiSearchTemplateResponse multiResponse = client.msearchTemplate(multiRequest, RequestOptions.DEFAULT);
		client.close();
		return multiResponse.getResponses();
	}
}
