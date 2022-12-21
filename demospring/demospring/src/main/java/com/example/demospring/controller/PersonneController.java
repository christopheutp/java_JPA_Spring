package com.example.demospring.controller;


import com.example.demospring.entity.Personne;
import com.example.demospring.interfaces.IPersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/personne")
public class PersonneController {

    @Autowired
    @Qualifier("personne2Service")
    private IPersonneService personneService;

    @GetMapping("")
   // public String getPersonnes(){return "Ma page pour affichers une liste de personnes";}
    public List<Personne> getPersonnes() { return personneService.getPersonnes();}

    @PostMapping("/post")
    public String postPersonne(){return "Ma page pour poster une personne";}


    @GetMapping("/{id}")
    public String getPersonne(@PathVariable Integer id){
        return "Ma page pour afficher une seule personne avec l'id "+id;}

    @PostMapping("")
    public Personne postPersonne(@RequestBody Personne personne){
        return personne;
    }

   /* @PostMapping("")
    public Personne postPersonne(@RequestParam String nom, @RequestParam String prenom) {
        return new Personne(1, nom, prenom);
    }*/
}
