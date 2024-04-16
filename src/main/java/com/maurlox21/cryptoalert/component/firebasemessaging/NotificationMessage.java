package com.maurlox21.cryptoalert.component.firebasemessaging;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationMessage {
    
    private String recipientToken;
    private String title;
    private String body;
    private String image;
    private Map<String, String> data;
}
