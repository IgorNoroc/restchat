package com.igornoroc.restchat.entities.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MessageResponseDTO {
    private String messageText;
    private Date created;
    private String username;

    public MessageResponseDTO(String messageText, Date created, String username) {
        this.messageText = messageText;
        this.created = created;
        this.username = username;
    }
}
