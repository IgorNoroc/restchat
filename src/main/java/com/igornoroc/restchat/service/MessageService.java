package com.igornoroc.restchat.service;

import com.igornoroc.restchat.entities.Message;
import com.igornoroc.restchat.entities.dto.MessageResponseDTO;

import java.util.Collection;

public interface MessageService {

    Collection<MessageResponseDTO> getAllMessage();

    void saveMessage(Message message);

    void delete(Message message);

    void deleteAll();
}
