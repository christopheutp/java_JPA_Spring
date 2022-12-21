package com.example.demospring.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Personne {
    private int id;
    private String nom;
    private String prenom;
}
