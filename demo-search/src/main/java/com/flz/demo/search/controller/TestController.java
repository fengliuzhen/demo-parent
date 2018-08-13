package com.flz.demo.search.controller;

import com.flz.demo.search.entity.OperationLog;
import com.flz.demo.search.service.OperationLogService;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;


@RestController
@RequestMapping("/test")
public class TestController {

    /*@Autowired
    private OperationLogService operationLogService;
*/
    @RequestMapping("/search")
    public String searchList(String keyword) throws Exception
    {
        //搜索条件
        Map<String, String> params = Collections.singletonMap("pretty", "true");
        String jsonString = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        HttpEntity entity = new NStringEntity(jsonString, ContentType.APPLICATION_JSON);
        //官方推荐的方法
        RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200, "http")).build();
        Response response = restClient.performRequest("GET", "/",params);
        String strBody= EntityUtils.toString(response.getEntity());

        restClient.close();
        return "";
    }
}
