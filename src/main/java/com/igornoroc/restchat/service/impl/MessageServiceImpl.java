package com.igornoroc.restchat.service.impl;


import com.igornoroc.restchat.entities.Message;
import com.igornoroc.restchat.entities.Person;
import com.igornoroc.restchat.entities.dto.MessageResponseDTO;
import com.igornoroc.restchat.repositories.MessageRepo;
import com.igornoroc.restchat.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepo messageRepo;
    private final PersonServiceImpl personService;

    public Collection<MessageResponseDTO> getAllMessage() {
        return allMessageForRoleUser();
    }

    public void saveMessage(Message message) {
        Person person = personService.findById(message.getPerson().getId());
        person.getMessages().add(message);
        personService.savePerson(person);
    }

    private Collection<MessageResponseDTO> allMessageForRoleUser() {
        Collection<Message> messages = messageRepo.findAllAndOrderByCreatedDateDescending();
        return messages.stream()
                .map(message -> new MessageResponseDTO(
                        message.getMessage(),
                        message.getCreated(),
                        message.getPerson().getUsername()))
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        messageRepo.deleteById(id);
    }

    public void deleteAll() {
        messageRepo.deleteAll();
    }
}
