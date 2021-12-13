package com.igornoroc.restchat.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messages")
@Data
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

    @PrePersist
    private void setCreateDate() {
        created = new Date(System.currentTimeMillis());
    }
}
