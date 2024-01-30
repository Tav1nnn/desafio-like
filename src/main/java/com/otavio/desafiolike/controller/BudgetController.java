package com.otavio.desafiolike.controller;

import com.otavio.desafiolike.controller.exception.model.StandardError;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/orcamento")
@Tag(name = "Orçamento de Produto", description = "API para fazer orçamentos de produtos")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService service) {
        this.budgetService = service;
    }

    @PostMapping(value = "/calcular")
    @Operation(summary = "Calcular orçamento", description = "Cliente seleciona os produtos e suas quantidades " +
            "para fazer o calculo do orçamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orçamento calculado com sucesso",
                content = @Content(schema = @Schema(implementation = ExitBudgetDto.class))),
            @ApiResponse(responseCode = "422", description = "Erro no nos dados inseridos",
            content = @Content(schema = @Schema(implementation = ValidationError.class)))
    })
    public ResponseEntity<ExitBudgetDto> calculateBudget (@Valid @RequestBody EntryBudgetDto entryBudget) {
        return ResponseEntity.ok().body(budgetService.calculateBudget(entryBudget));
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
        budgetService.saveBudget(entryBudget);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @DeleteMapping(value = "/deletar/{id}")
    @Operation(summary = "Deletar orçamento", description = "Deleta o orçamento de acordo com o ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orçamento deletado com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "id não encontrado",
                    content =  @Content(schema = @Schema(implementation = StandardError.class)))
    })
    public void deleteBudget (@PathVariable("id") Long id) {
        budgetService.deleteBudget(id);
    }

    @GetMapping("/buscarPorId/{id}")
    @Operation(summary = "Busca por ID",description = "Busca o orçamento de acordo com o ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso na busca",
                    content =  @Content(schema = @Schema(implementation = ExitBudgetDto.class))),
            @ApiResponse(responseCode = "404", description = "ID não encontrado",
                    content =  @Content(schema = @Schema(implementation = StandardError.class)))
    })
    public ResponseEntity<ExitBudgetDto> findByIdBudget(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(budgetService.findByIdBudget(id));
    }

    @GetMapping("/buscar")
    @Operation(summary = "Busca todos ",description = "Busca todos os orçamentos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso na busca",
                    content =  @Content(schema = @Schema(implementation = ExitBudgetDto.class))),
    })
    public ResponseEntity<List<ExitBudgetDto>> findByIdBudget() {
        return ResponseEntity.ok().body(budgetService.findAllBudget());
    }
}
