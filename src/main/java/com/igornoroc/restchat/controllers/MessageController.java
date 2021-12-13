package com.igornoroc.restchat.controllers;

import com.igornoroc.restchat.entities.Message;
import com.igornoroc.restchat.entities.dto.MessageResponseDTO;
import com.igornoroc.restchat.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PostMapping("/add")
    public void addMessage(@RequestBody Message message) {
        messageService.saveMessage(message);
    }

    @GetMapping("/all")
    public Collection<MessageResponseDTO> getAllMessages() {
        return messageService.getAllMessage();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete{id}")
    public void deleteMessage(@PathVariable Long id) {
        messageService.delete(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteAll")
    public void deleteAllMessages() {
        messageService.deleteAll();
    }
}
