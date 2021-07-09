package com.devcamp.groupone.sms.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Anugrah Prasetia
 * @date 10/07/2021 1:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendMessageRequest {
    private String number;
    private String message;
}
