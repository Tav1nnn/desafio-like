package com.otavio.desafiolike.controller;

import com.otavio.desafiolike.service.BudgetService;
import com.otavio.desafiolike.controller.exception.model.ValidationError;
import com.otavio.desafiolike.dto.EntryBudgetDto;
import com.otavio.desafiolike.dto.ExitBudgetDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/orcamento")
@Tag(name = "Orçamento de Produto", description = "API para fazer orçamentos de produtos")
public class BudgetController {

    private final BudgetService service;

    public BudgetController(BudgetService service) {
        this.service = service;
    }


    @Operation(summary = "Calcular orçamento", description = "Cliente seleciona os produtos e suas quantidades " +
            "para fazer o calculo do orçamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orçamento calculado com sucesso",
                content = @Content(schema = @Schema(implementation = ExitBudgetDto.class))),
            @ApiResponse(responseCode = "422", description = "Erro no nos dados inseridos",
            content = @Content(schema = @Schema(implementation = ValidationError.class)))
    })
    @PostMapping(value = "/calcular")
    public ResponseEntity<ExitBudgetDto> calculateBudget (@Valid @RequestBody EntryBudgetDto entryBudget) {
        return ResponseEntity.ok().body(service.calculateBudget(entryBudget));
    }
    @PostMapping(value = "/confirmar")
    @Operation(summary = "Confirmar orçamento", description = "Cliente cofirma o orçamento" +
            "para salvar na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Orçamento salco com sucesso"),
            @ApiResponse(responseCode = "422", description = "Erro no nos dados inseridos",
                    content = @Content(schema = @Schema(implementation = ValidationError.class)))
    })
    public void confirmBudget (@Valid @RequestBody EntryBudgetDto entryBudget, HttpServletResponse response) {
        service.saveBudget(entryBudget);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }
}
