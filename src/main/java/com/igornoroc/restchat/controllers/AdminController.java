package com.igornoroc.restchat.controllers;

import com.google.gson.Gson;
import com.igornoroc.restchat.entities.Message;
import com.igornoroc.restchat.entities.Person;
import com.igornoroc.restchat.entities.Role;
import com.igornoroc.restchat.service.impl.MessageServiceImpl;
import com.igornoroc.restchat.service.impl.PersonServiceImpl;
import com.igornoroc.restchat.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Value("${ADMIN_ROLE_ID}")
    private long ADMIN_ROLE_ID;
    private final PersonServiceImpl personService;
    private final RoleServiceImpl roleService;
    private final MessageServiceImpl messageService;

    public AdminController(PersonServiceImpl personService, RoleServiceImpl roleService, MessageServiceImpl messageService) {
        this.personService = personService;
        this.roleService = roleService;
        this.messageService = messageService;
    }

    @GetMapping("/allPerson")
    public String allPersons() {
        return new Gson().toJson(
                personService.getAllPersons());
    }

    @PostMapping("/setNewPersonRole")
    public void addNewPerson(@RequestBody Person person) {
        Person findPerson = personService.findById(person.getId());
        if (findPerson != null) {
            findPerson.
                    getRoles().add(
                            roleService.findRoleById(ADMIN_ROLE_ID));
        } else {
            throw new IllegalArgumentException(
                    String.format("person with id: %d not found", person.getId())
            );
        }
        personService.savePerson(findPerson);
    }

    @PostMapping("/deletePerson")
    public void deletePerson(@RequestBody Person person) {
        personService.delete(person);
    }

    @PostMapping("/deleteAllPerson")
    public void deleteAllPerson() {
        personService.deleteAll();
    }

    @PostMapping("/deleteMessage")
    public void deletePerson(@RequestBody Message message) {
        messageService.delete(message);
    }

    @PostMapping("/deleteAllMessages")
    public void deleteAllMessages() {
        messageService.deleteAll();
    }

    @PostMapping("/addRole")
    public void addRole(@RequestBody Role role) {
        roleService.save(role);
    }

    @PostMapping("/deleteRole")
    public void deleteRole(@RequestBody Role role) {
        roleService.delete(role);
    }

    @PostMapping("/deleteAllRole")
    public void deleteAllRoles() {
        roleService.deleteAll();
    }
}
