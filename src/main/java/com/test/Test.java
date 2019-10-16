package com.test;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Test {

	// on startup
	public static void getRequest() throws IOException {
		RestHighLevelClient client = new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http"), new HttpHost("localhost", 9200, "http")));
		System.out.println(client);

		GetRequest getRequest = new GetRequest("bank", "_doc", "1");
		System.out.println(getRequest);
		
		client.close();
	}

}
