package com.flz.demo.common;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HttpUtils {

    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class.getName());
    private static RestTemplate restTemplate;

    public <T> T get(String url, Class<T> clazz) {
        log.debug("RestClient get start, url:{}", url);
        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }
        ResponseEntity<T> result = restTemplate.getForEntity(url, clazz);
        T body = result.getBody();
        log.debug("RestClient get end, url:{}", url);
        return body;
    }

    public <T> T post(String url, Object obj, Class<T> clazz) {
        log.debug("RestClient PostAsJson start, url:{}", url);
        String data = new Gson().toJson(obj);
        T post = post(url, clazz, MediaType.APPLICATION_JSON_UTF8, data);
        log.debug("RestClient PostAsJson end, url:{}, data: {}", url, data);
        return post;
    }

    public <T> T post(String url, Class<T> clazz, MediaType mediaType, String data) {
        log.debug("RestClient Post start, url:{}", url);
        if (restTemplate == null)
        {
            restTemplate = new RestTemplate();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        HttpEntity<String> entity = new HttpEntity<String>(data, headers);
        ResponseEntity<T> result = restTemplate.postForEntity(url, entity, clazz);
        T body = result.getBody();
        log.debug("RestClient Post end, url:{}, data: {}", url, data);
        return body;
    }
}
