package com.otavio.desafiolike.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/teste")
public class teste {

    @GetMapping
    public String teste() {
        return "testado";
    }
}
