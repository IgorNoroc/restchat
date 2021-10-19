package com.igornoroc.restchat.service.impl;

import com.igornoroc.restchat.entities.Person;
import com.igornoroc.restchat.repositories.PersonRepo;
import com.igornoroc.restchat.service.PersonService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepo personRepo;
    private final RoleServiceImpl roleService;
    @Value("${DEFAULT_ROLE_ID}")
    private long DEFAULT_ROLE_ID;

    public PersonServiceImpl(PersonRepo personRepo, RoleServiceImpl roleService) {
        this.personRepo = personRepo;
        this.roleService = roleService;
    }

    public void savePerson(Person person) {
        if (isNewPerson(person)) {
            setDefaultRole(person);
        }
        personRepo.save(person);
    }


    public Person findByName(String personName) {
        return personRepo.findByUsername(personName);
    }

    public Collection<Person> getAllPersons() {
        return personRepo.findAll();
    }

    public Person findById(long id) {
        return personRepo.findById(id).orElse(null);
    }

    public void delete(Person person) {
        personRepo.delete(person);
    }

    public void deleteAll() {
        personRepo.deleteAll();
    }

    /**
     * If person is new we set default role.
     *
     * @param person new person
     */
    private void setDefaultRole(Person person) {
        person.getRoles().add(
                roleService.findRoleById(DEFAULT_ROLE_ID));
    }

    private boolean isNewPerson(Person person) {
        Person result = personRepo.findByUsername(person.getUsername());
        return result == null;
    }
}
