package com.shing.leetmaster.esdao;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.MainResponse;

/**
 * 测试 Elasticsearch 连接
 *
 * @Author: shing
 */
public class ElasticsearchConnectionTest {
    public static void main(String[] args) {
        // 创建 RestHighLevelClient
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")
                )
        );

        try {
            // 测试连接
            MainResponse response = client.info(RequestOptions.DEFAULT);
            System.out.println("Connected to Elasticsearch cluster: " + response.getClusterName());
            System.out.println("Version: " + response.getVersion().getNumber());
        } catch (Exception e) {
            System.err.println("Failed to connect to Elasticsearch: " + e.getMessage());
        } finally {
            try {
                client.close();
            } catch (Exception e) {
                System.err.println("Failed to close client: " + e.getMessage());
            }
        }
    }
}
