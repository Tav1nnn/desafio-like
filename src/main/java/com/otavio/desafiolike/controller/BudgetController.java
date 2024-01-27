package com.otavio.desafiolike.controller;

import com.otavio.desafiolike.Service.BudgetService;
import com.otavio.desafiolike.dto.EntryBudget;
import com.otavio.desafiolike.dto.ExitBudget;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/budget")
public class BudgetController {

    private final BudgetService service;

    public BudgetController(BudgetService service) {
        this.service = service;
    }

    @PostMapping(value = "/calculateBudget")
    public ResponseEntity<ExitBudget> calculateBudget (@Valid @RequestBody EntryBudget entryBudget) {
        System.out.println("size" + entryBudget.getProducts().size());
        return ResponseEntity.ok().body(service.calculateBudget(entryBudget));
    }
}
