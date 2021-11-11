package com.igornoroc.restchat.service.impl;

import com.igornoroc.restchat.entities.Person;
import com.igornoroc.restchat.exception.UserNotFoundException;
import com.igornoroc.restchat.repositories.PersonRepo;
import com.igornoroc.restchat.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService, UserDetailsService {
    @Value("${DEFAULT_ROLE_ID}")
    private long DEFAULT_ROLE_ID;
    private final PersonRepo personRepo;
    private final RoleServiceImpl roleService;

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
        return personRepo.findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException(
                                String.format("person with id: %d not found", id)));
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
                roleService.findById(DEFAULT_ROLE_ID));
    }

    private boolean isNewPerson(Person person) {
        Person result = personRepo.findByUsername(person.getUsername());
        return result == null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person user = personRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(
                    String.format("user %s not found!", username));
        }
        return new User(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>());
    }
}
