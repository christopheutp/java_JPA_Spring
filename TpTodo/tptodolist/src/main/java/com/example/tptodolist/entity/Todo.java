package com.example.tptodolist.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "todo")
@Data
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String description;

    private Date date;

    private boolean status;

    public Todo(){

    }
    public Todo(String title, String description) {
        this.setTitle(title);
        this.setDescription(description);
        this.setStatus(false);
        this.setDate(new Date());
    }

}
