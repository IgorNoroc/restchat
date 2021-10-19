package com.igornoroc.restchat.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.igornoroc.restchat.entities.Person;
import com.igornoroc.restchat.service.impl.PersonServiceImpl;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class RegistrationController {
    private final PersonServiceImpl service;
    private final BCryptPasswordEncoder encoder;
    private final ObjectMapper objectMapper;

    public RegistrationController(PersonServiceImpl service, BCryptPasswordEncoder encoder, ObjectMapper objectMapper) {
        this.service = service;
        this.encoder = encoder;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/registration")
    public void login(@RequestBody Person person) {
        person.setPassword(encoder.encode(person.getPassword()));
        service.savePerson(person);
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public void exceptionHandler(Exception e, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() {
            {
                put("message", "username already exists");
                put("type", e.getClass());
            }
        }));
    }
}

