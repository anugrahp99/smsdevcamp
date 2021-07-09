package com.devcamp.groupone.sms.service;

import com.devcamp.groupone.sms.payload.request.SendMessageRequest;

import java.net.URISyntaxException;

/**
 * @author Anugrah Prasetia
 * @date 10/07/2021 5:06
 */
public interface MessageService {
    public String sendMessage(SendMessageRequest request) throws URISyntaxException;
}
