package com.example.demospring.classe;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaClasse {

    @Autowired
    ClasseA classeA;

    @Autowired
    ClasseB classeB;

}
