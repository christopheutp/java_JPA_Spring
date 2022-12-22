package com.example.demospring.controller;


import com.example.demospring.entity.Produit;
import com.example.demospring.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/produit")
@ResponseBody
public class ProduitController {

    @Autowired
    ProduitService produitService;


    @GetMapping("")
    public List<Produit> getProduits(){
        return produitService.findAll();
    }

    @GetMapping("/{id}")
    public Produit getProduit(@PathVariable("id") Integer id) {
        return produitService.findById(id);
    }

    @GetMapping("/getproduit")
    public Produit getProduitByParams(@RequestParam("id") Integer id){
        return produitService.findById(id);
    }

    @PostMapping("")
    public Produit postProduit(@RequestBody Produit produit){
        if(produitService.create(produit)) {
            return produit;
        }
        return null;
    }

}
