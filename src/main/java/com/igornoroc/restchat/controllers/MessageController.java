package com.igornoroc.restchat.controllers;

import com.igornoroc.restchat.entities.Message;
import com.igornoroc.restchat.entities.dto.MessageResponseDTO;
import com.igornoroc.restchat.service.impl.MessageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageServiceImpl messageService;

    @PostMapping("/add")
    public void addMessage(@RequestBody Message message) {
        messageService.saveMessage(message);
    }

    @GetMapping("/all")
    public Collection<MessageResponseDTO> getAllMessages() {
        return messageService.getAllMessage();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete")
    public void deletePerson(@RequestBody Message message) {
        messageService.delete(message);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllMessages() {
        messageService.deleteAll();
    }
}
