package com.igornoroc.restchat.service;

import com.igornoroc.restchat.entities.Person;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;

public interface PersonService extends UserDetailsService {

    void savePerson(Person person);

    Person findByName(String personName);

    Collection<Person> getAllPersons();

    Person findById(long id);

    void setAdminRole(Long personId, Long roleAdminId);

    void delete(Long id);

    void deleteAll();
}
