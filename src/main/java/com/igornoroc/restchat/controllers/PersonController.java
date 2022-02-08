package com.igornoroc.restchat.controllers;

import com.igornoroc.restchat.entities.Person;
import com.igornoroc.restchat.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController()
@RequestMapping("/api/person")
@RequiredArgsConstructor
public class PersonController {
    @Value("${ADMIN_ROLE_ID}")
    private long ADMIN_ROLE_ID;
    private final PersonService personService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public Collection<Person> allPersons() {
        return personService.getAllPersons();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/setAdminRole{id}")
    public void setRoleAdminForPerson(@PathVariable Long id) {
        personService.setAdminRole(id, ADMIN_ROLE_ID);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.delete(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteAll")
    public void deleteAllPerson() {
        personService.deleteAll();
    }
}
