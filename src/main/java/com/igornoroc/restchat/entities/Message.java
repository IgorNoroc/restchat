package com.igornoroc.restchat.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "messages")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String message;

    @JsonFormat(pattern = "DD-MMMM-yyyy HH:mm:ss")
    private Date created;

    @ManyToOne()
    @JoinColumn(name = "person_id")
    private Person person;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return id == message1.id && Objects.equals(message, message1.message) && Objects.equals(created, message1.created) && Objects.equals(person, message1.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, created, person);
    }
}
