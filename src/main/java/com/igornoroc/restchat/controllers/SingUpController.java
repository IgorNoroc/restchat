package com.igornoroc.restchat.controllers;

import com.igornoroc.restchat.entities.Person;
import com.igornoroc.restchat.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SingUpController {
    private final PersonService service;
    private final BCryptPasswordEncoder encoder;

    @PostMapping("/registration")
    public String login(@RequestBody @Valid Person person) {
        person.setPassword(encoder.encode(person.getPassword()));
        service.savePerson(person);
        return String.format(
                "person named %s was been registered. New id : %d", person.getUsername(), person.getId());
    }
}

