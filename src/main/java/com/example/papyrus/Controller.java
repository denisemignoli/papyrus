package com.example.papyrus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    
    @GetMapping("/")
    public String home(){
        return "Seja bem-vindo ao Papyrus!";
    }
}
