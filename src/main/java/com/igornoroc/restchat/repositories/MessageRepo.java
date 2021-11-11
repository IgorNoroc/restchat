package com.igornoroc.restchat.repositories;

import com.igornoroc.restchat.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {
    @Query(nativeQuery = true, value = "select * from messages order by created desc")
    Collection<Message> findAllAndOrderByCreatedDateDescending();
}
