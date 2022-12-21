package com.example.demospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/personne")
public class PersonneController {

    @GetMapping("/")
    public String getPersonnes(){return "Ma page pour affichers une liste de personnes";}

    @PostMapping("/post")
    public String postPersonne(){return "Ma page pour poster une personne";}

    @GetMapping("/id")
    public String getPersonne(){ return "Ma page pour afficher une seule personne";}
}
