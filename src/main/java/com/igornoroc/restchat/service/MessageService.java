package com.igornoroc.restchat.service;

import com.igornoroc.restchat.entities.Message;

import java.util.Collection;

public interface MessageService {

    Collection<Message> getAllMessage();

    void saveMessage(Message message);

    void delete(Message message);

    void deleteAll();
}
