package com.example.demospring.service;

import com.example.demospring.entity.Personne;
import com.example.demospring.interfaces.IPersonneService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PersonneService implements IPersonneService {
    @Override
    public List<Personne> getPersonnes() {
        return Arrays.asList(new Personne(1,"toto","tata"),new Personne(2,"titi","minet"));
    }
}
