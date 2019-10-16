package com.test;


import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.open.OpenIndexRequest;
import org.elasticsearch.action.admin.indices.open.OpenIndexResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	public RestHighLevelClient createConnection() {
        return new RestHighLevelClient(RestClient.builder(new HttpHost("http://192.168.22.55",9200,"http")));
    }
	
	public TestController() {
		System.out.println("**********Controller************");
		System.out.println("**********Controller************");
		System.out.println("**********Controller************");
		System.out.println("**********Controller************");
	}
	
	 @RequestMapping("/open")
	    public Object open() {
	        
	        boolean acknowledged = false;
	        
	        try(
	                RestHighLevelClient client = createConnection();
	        ){
	            //index name
	            String indexName = "songaa";
	            //인덱스 open
	            OpenIndexRequest request = new OpenIndexRequest(indexName);
	            OpenIndexResponse response = client.indices().open(request, RequestOptions.DEFAULT);
	            acknowledged = response.isAcknowledged();
	        }catch (Exception e) {
	            // TODO: handle exception
	            e.printStackTrace();
	            return "인덱스 open에 실패하였습니다. - catch";
	        }
	        
	        return acknowledged == true ? "인덱스를 open 하였습니다.":"인덱스 open에 성공하였습니다.";
	    }
	 
	 
	@CrossOrigin(origins = "http://localhost:8000")
	@GetMapping("/test")
	public Object test() {
		//인덱스 별칭
        String aliasName = "song";
        //문서 타입
        String typeName ="_doc";
        //문서 ID
        String docId = "1";
        
        //문서조회 요청
        GetRequest request = new GetRequest(aliasName,typeName,docId);
        
        //문서조회 결과
        GetResponse response = null;
        RestHighLevelClient client = createConnection();
        try{
            System.out.println("조회 시작");
            response = client.get(request, RequestOptions.DEFAULT);
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return "문서조회에 실패하였습니다.";
        }finally {
        	try {
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
//        Map<String, Object> sourceAsMap = null;
//        if(response.isExists()) {
//            long version = response.getVersion();
//            sourceAsMap = response.getSourceAsMap();
//        }
        
        return response;

	}
}
