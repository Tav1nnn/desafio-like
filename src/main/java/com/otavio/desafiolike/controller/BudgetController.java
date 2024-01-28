package com.otavio.desafiolike.controller;

import com.otavio.desafiolike.Service.BudgetService;
import com.otavio.desafiolike.dto.EntryBudgetDto;
import com.otavio.desafiolike.dto.ExitBudgetDto;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
@RequestMapping(value = "/api/v1/orcamento")
public class BudgetController {

    private final BudgetService service;

    public BudgetController(BudgetService service) {
        this.service = service;
    }

    @PostMapping(value = "/calcular")
    public ResponseEntity<ExitBudgetDto> calculateBudget (@Valid @RequestBody EntryBudgetDto entryBudget) {
        System.out.println("size" + entryBudget.getProducts().size());
        return ResponseEntity.ok().body(service.calculateBudget(entryBudget));
    }
    @PostMapping(value = "/confirmar")
    public void confirmBudget (@Valid @RequestBody EntryBudgetDto entryBudget, HttpServletResponse response) {
        service.saveBudget(entryBudget);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }
}
