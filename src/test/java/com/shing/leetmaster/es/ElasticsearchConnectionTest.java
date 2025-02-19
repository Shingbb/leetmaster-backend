package com.shing.leetmaster.es;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ElasticsearchConnectionTest {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void testElasticsearchConnection() throws IOException {
        // Send a GET request to _cluster/health
        Request request = new Request("GET", "/_cluster/health");
        Response response = restHighLevelClient.getLowLevelClient().performRequest(request);

        // Assert that the status code is 200
        int statusCode = response.getStatusLine().getStatusCode();
        assertEquals(200, statusCode, "Elasticsearch connection failed!");

        // Read the response body
        HttpEntity entity = response.getEntity();
        String responseBody = entity != null ? EntityUtils.toString(entity) : "";

        // Print the response for debugging
        System.out.println("Elasticsearch Connection Successful: " + response.getStatusLine());
        System.out.println("Response Body: " + responseBody);
    }
}
