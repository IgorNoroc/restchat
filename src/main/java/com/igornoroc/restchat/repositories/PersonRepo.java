package com.igornoroc.restchat.repositories;

import com.igornoroc.restchat.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {

    Person findByUsername(String personName);
}
