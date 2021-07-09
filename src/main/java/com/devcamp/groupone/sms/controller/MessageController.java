package com.devcamp.groupone.sms.controller;

import com.devcamp.groupone.sms.constant.ApiPath;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Anugrah Prasetia
 * @date 10/07/2021 0:56
 */

@RestController
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    WebClient webClient;

    @GetMapping(value = "/api/send")
    public String sendMessage() throws URISyntaxException {
        MultiValueMap<String, String> bodyValues = new LinkedMultiValueMap<>();
        bodyValues.add("phone_number", "081915815742");
        bodyValues.add("message", "Tes smsgateway.me");
        bodyValues.add("device_id", "125029");

        List<MultiValueMap> data = new ArrayList<>();
        data.add(bodyValues);
        data.add(bodyValues);

        String datas = "[\n" +
                "  {\n" +
                "    \"phone_number\": \"081915815742\",\n" +
                "    \"message\": \"string\",\n" +
                "    \"device_id\": 125029\n" +
                "  }\n" +
                "]";

        String response =this.webClient
                .post()
                .uri(new URI("https://smsgateway.me/api/v4/message/send"))
                .body(BodyInserters.fromObject(datas))
                .header("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhZG1pbiIsImlhdCI6MTYyNTg1NDkwNywiZXhwIjo0MTAyNDQ0ODAwLCJ1aWQiOjg5NDkyLCJyb2xlcyI6WyJST0xFX1VTRVIiXX0.DdexTqgCQHtnXrI2bQUcAdOxam_gFLPes59EEyU0z7g")
                .header("Content-Type", "application/json")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return response;
    }

    @GetMapping(value = "/api/callback")
    public String setCallback() throws URISyntaxException {
        String datas  = "{\n" +
                "  \"name\": \"Test Callback\",\n" +
                "  \"event\": \"MESSAGE_RECEIVED\",\n" +
                "  \"device_id\": 125029,\n" +
                "  \"filter_type\": \"contains\",\n" +
                "  \"filter\": \"beli\",\n" +
                "  \"method\": \"http\",\n" +
                "  \"action\": \"http://mywebsite/sms-gateway-me-hook\",\n" +
                "  \"secret\": \"super-secret\"\n" +
                "}";
        String response =this.webClient
                .post()
                .uri(new URI("https://smsgateway.me/api/v4/callback"))
                .body(BodyInserters.fromObject(datas))
                .header("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhZG1pbiIsImlhdCI6MTYyNTg1NDkwNywiZXhwIjo0MTAyNDQ0ODAwLCJ1aWQiOjg5NDkyLCJyb2xlcyI6WyJST0xFX1VTRVIiXX0.DdexTqgCQHtnXrI2bQUcAdOxam_gFLPes59EEyU0z7g")
                .header("Content-Type", "application/json")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return response;
    }

    @PostMapping(value = "api/callback/received")
    public String getcallbackpost(Object data){
        logger.info("post callback response: {}", String.valueOf(data));
        return "sukses";
    }

    @GetMapping(value = "api/callback/received")
    public String getcallbackget(Object data){
        logger.info("get call back response: {}", String.valueOf(data));
        return "sukses";
    }
}
