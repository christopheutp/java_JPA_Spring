package com.example.demospring.controller;


import com.example.demospring.entity.Produit;
import com.example.demospring.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/produit")
@ResponseBody
public class ProduitController {

    @Autowired
    ProduitService produitService;

    @PostMapping("")
    public Produit postProduit(@RequestBody Produit produit){
        if(produitService.create(produit)) {
            return produit;
        }
        return null;
    }

}
