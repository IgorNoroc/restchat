package com.igornoroc.restchat.service.impl;


import com.igornoroc.restchat.entities.Message;
import com.igornoroc.restchat.entities.Person;
import com.igornoroc.restchat.repositories.MessageRepo;
import com.igornoroc.restchat.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepo messageRepo;
    private final PersonServiceImpl personService;

    public MessageServiceImpl(MessageRepo messageRepo, PersonServiceImpl personService) {
        this.messageRepo = messageRepo;
        this.personService = personService;
    }

    public Collection<Message> getAllMessage() {
        return sortByCreateDate(
                messageRepo.findAll()
        );
    }

    public void saveMessage(Message message) {
        message.setCreated(
                new Date(
                        System.currentTimeMillis()));
        Person person = personService.findById(message.getPerson().getId());
        person.getMessages().add(message);
        personService.savePerson(person);
    }

    private Collection<Message> sortByCreateDate(List<Message> messages) {
        Comparator<Message> comparator = (o1, o2) -> o2.getCreated().compareTo(o1.getCreated());
        messages.sort(comparator);
        return messages;
    }

    public void delete(Message message) {
        messageRepo.delete(message);
    }

    public void deleteAll() {
        messageRepo.deleteAll();
    }
}
