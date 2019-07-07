package com.rz.frame.utils;


import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ElasticUtils {

	private static RestHighLevelClient restClient = null;

	static {
		if (restClient == null) {
			restClient = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.0.108", 9200, "http")));
		}
	}

	public static void add(String indexName, String type, JSONObject docment) {
		try {

			IndexRequest indexRequest = new IndexRequest(indexName, type);
			indexRequest.source(docment.toString(), XContentType.JSON);
			restClient.index(indexRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
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
			restClient.bulk(request,RequestOptions.DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public static void addListByPage(String indexName, String type, List<JSONObject> docments) {
		try {
			int pagesize = 10000;
			if (docments.size() <= pagesize) {
				addList(indexName, type, docments);
				return;
			}
			int pageIndex = docments.size() / pagesize;
			for (int i = 0; i < pageIndex; i++) {
				List<JSONObject> tempList = docments.stream().skip(i * pagesize).limit(pagesize).collect(Collectors.toList());
				addList(indexName, type, tempList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void delete(String indexName, String type, List<JSONObject> docments) {
		try {

			IndexRequest indexRequest = new IndexRequest(indexName, type);
			for (JSONObject docment : docments) {
				indexRequest.source(docment.toString(), XContentType.JSON);
				restClient.index(indexRequest,RequestOptions.DEFAULT);
			}
		} catch (IOException e) {
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
			SearchResponse searchResponse = restClient.search(searchRequest,RequestOptions.DEFAULT);
			return searchResponse;
		} catch (Exception ex) {
			return null;
		}


	}

	public static DeleteResponse delete(String indexName, String type, JSONObject docment) {


		try {
			DeleteRequest deleteRequest = new DeleteRequest(indexName);
			deleteRequest.type(type);

			DeleteResponse deleteResponse = restClient.delete(deleteRequest,RequestOptions.DEFAULT);
			return deleteResponse;
		} catch (Exception ex) {
			return null;
		}
	}
}


