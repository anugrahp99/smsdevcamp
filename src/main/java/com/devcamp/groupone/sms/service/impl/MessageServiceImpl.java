package com.devcamp.groupone.sms.service.impl;

import com.devcamp.groupone.sms.controller.MessageController;
import com.devcamp.groupone.sms.payload.request.SendMessageRequest;
import com.devcamp.groupone.sms.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Anugrah Prasetia
 * @date 10/07/2021 5:07
 */
@Service
public class MessageServiceImpl implements MessageService {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    WebClient webClient;

    @Override
    public String sendMessage(SendMessageRequest request) throws URISyntaxException {
        String datas = "[\n" +
                "  {\n" +
                "    \"phone_number\": \""+request.getNumber()+"\",\n" +
                "    \"message\": \""+request.getMessage()+"\",\n" +
                "    \"device_id\": 125029\n" +
                "  }\n" +
                "]";
        try{
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
        } catch (Exception e){
            return e.getMessage();
        }
    }
}
