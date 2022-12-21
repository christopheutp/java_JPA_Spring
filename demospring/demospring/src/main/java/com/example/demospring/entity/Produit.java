package com.example.demospring.entity;


import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;

@Entity
@Data
public class Produit {

    @Id
    @GeneratedValue
    private int id;
    private String marque;
    private String reference;
    @Temporal(TemporalType.DATE)
    private Date dateAchat;
    private double prix;
    private int stock;
}
