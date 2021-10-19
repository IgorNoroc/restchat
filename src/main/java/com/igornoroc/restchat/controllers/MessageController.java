package com.igornoroc.restchat.controllers;

import com.google.gson.Gson;
import com.igornoroc.restchat.entities.Message;
import com.igornoroc.restchat.service.impl.MessageServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/api/messages")
public class MessageController {
    private final MessageServiceImpl messageService;

    public MessageController(MessageServiceImpl messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/add")
    public void addMessage(@RequestBody Message message) {
        messageService.saveMessage(message);
    }

    @GetMapping("/all")
    public String getAllMessages() {
        return new Gson().toJson(
                messageService.getAllMessage());
    }
}
