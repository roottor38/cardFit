package com.cardfit.project.config;
import org.apache.http.HttpHost;

import org.elasticsearch.client.RestClient;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class ELConfiguration {
	public RestHighLevelClient clientConnection() {
		RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.22.85", 9200, "http")));
		return client;
	}
}
