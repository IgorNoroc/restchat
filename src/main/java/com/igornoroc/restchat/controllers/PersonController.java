package com.igornoroc.restchat.controllers;

import com.igornoroc.restchat.entities.Person;
import com.igornoroc.restchat.service.PersonService;
import com.igornoroc.restchat.service.RoleService;
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
    private final RoleService roleService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public Collection<Person> allPersons() {
        return personService.getAllPersons();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/setAdminRole")
    public void setRoleAdminForPerson(@RequestBody Person person) {
        Person findPerson = personService.findById(person.getId());
        findPerson.
                getRoles().add(
                        roleService.findById(ADMIN_ROLE_ID));
        personService.savePerson(findPerson);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete")
    public void deletePerson(@RequestBody Person person) {
        personService.delete(person);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteAll")
    public void deleteAllPerson() {
        personService.deleteAll();
    }
}
