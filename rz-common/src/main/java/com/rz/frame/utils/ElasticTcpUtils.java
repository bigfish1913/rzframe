package com.rz.frame.utils;

import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ElasticTcpUtils {
	static TransportClient client = null;
	static {
		try {
			Settings settings = Settings.builder().put("cluster.name", "elastictest").put("node.name", "node-1").put("client.transport.sniff", true).build();
			client = new PreBuiltTransportClient(settings).addTransportAddress(new TransportAddress(InetAddress.getByName("10.32.121.3"), 9300));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	public static void add(String indexName, String type, JSONObject docment) {
		try {
			IndexRequest indexRequest = new IndexRequest(indexName, type);
			indexRequest.source(docment.toString(), XContentType.JSON);
			client.index(indexRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void addList(String indexName, String type, List<JSONObject> docments) {
		try {
			
			BulkRequest request = new BulkRequest();
			IndexRequest indexRequest = new IndexRequest(indexName, type);
			for (JSONObject docment : docments) {
				request.add(indexRequest.source(docment.toJSONString(), XContentType.JSON));
			}
			client.bulk(request);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	public static SearchResponse searchByPage(String indexName, String type, int index, int size) {
		try {
			SearchRequest searchRequest = new SearchRequest(indexName);
			searchRequest.types(type);
			//			searchRequest.
			
			// 2、用SearchSourceBuilder来构造查询请求体 ,请仔细查看它的方法，构造各种查询的方法都在这。
			SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
			sourceBuilder.query(QueryBuilders.matchAllQuery());
			sourceBuilder.from((index - 1) * size);
			sourceBuilder.size(size);
			sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
			SearchResponse searchResponse = client.search(searchRequest).actionGet();
			return searchResponse;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
		
		
	}
}
