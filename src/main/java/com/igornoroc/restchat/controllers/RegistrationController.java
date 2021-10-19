package com.igornoroc.restchat.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.igornoroc.restchat.entities.Person;
import com.igornoroc.restchat.service.impl.PersonServiceImpl;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class RegistrationController {
    private final PersonServiceImpl service;
    private final BCryptPasswordEncoder encoder;

    public RegistrationController(PersonServiceImpl service, BCryptPasswordEncoder encoder) {
        this.service = service;
        this.encoder = encoder;
    }

    @PostMapping("/registration")
    public void login(@RequestBody @Valid Person person) {
        person.setPassword(encoder.encode(person.getPassword()));
        service.savePerson(person);
    }
}

