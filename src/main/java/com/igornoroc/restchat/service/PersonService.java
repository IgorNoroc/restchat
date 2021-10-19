package com.igornoroc.restchat.service;

import com.igornoroc.restchat.entities.Person;

import java.util.Collection;

public interface PersonService {

    void savePerson(Person person);

    Person findByName(String personName);

    Collection<Person> getAllPersons();

    Person findById(long id);

    void delete(Person person);

    void deleteAll();
}
