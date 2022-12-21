package com.example.demospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // => HomeController est spring beans de type controller
@ResponseBody // => retour des méthodes qui sera directement la réponse des reqûetes
public class HomeController {
        // http://localhost:8080/
    // Get et Post

    @RequestMapping("/")
    public String getHome() {return "Hello World";}

    @RequestMapping("/toto")
    public String getToto(){return "je suis la page toto";}

    // uniquement avec du GET
    @GetMapping("/get")
    public String pageGet(){return "une page avec du get";}

    // uniquement avec du post
    @PostMapping("/post")
    public String pagePost(){return "une page uniquement avec du post";}

}
