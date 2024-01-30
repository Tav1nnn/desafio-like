package com.otavio.desafiolike.service;

import com.otavio.desafiolike.dto.*;
import com.otavio.desafiolike.entity.BudgetEntity;
import com.otavio.desafiolike.service.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import com.otavio.desafiolike.repository.BudgetRepositoy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BudgetService {

    private final BudgetRepositoy budgetRepositoy;
    private final BudgetProductService budgetProductService;
    public BudgetService(BudgetRepositoy budgetRepositoy, BudgetProductService budgetProductService) {
        this.budgetRepositoy = budgetRepositoy;
        this.budgetProductService = budgetProductService;
    }

    public ExitBudgetDto calculateBudget(EntryBudgetDto entryBudget) {
        Set<ExitBudgetProductDto> exitBudgetProductSet = new HashSet<>();
        double totalBudget = 0.0;

        for(EntryBudgetProductDto product : entryBudget.getProducts()) {
            double totalValue = product.getQuantity() * product.getProductValue();
            totalBudget += totalValue;
            ExitBudgetProductDto exitProduct = new ExitBudgetProductDto(
                    product.getProductName(),
                    product.getProductValue(),
                    product.getQuantity(),
                    totalValue
            );
            exitBudgetProductSet.add(exitProduct);
        }

        return new ExitBudgetDto(
                entryBudget.getClientName(),
                entryBudget.getDate(),
                exitBudgetProductSet,
                totalBudget
        );
    }

    public void saveBudget(EntryBudgetDto entryBudget) {
        BudgetEntity entity = new BudgetEntity();
        entity.setClientName(entryBudget.getClientName());
        entity.setDate(entryBudget.getDate());


       entity = budgetRepositoy.save(entity);

        BudgetDto dto = budgetEntityToBudgetDto(entity);

        budgetProductService.saveBudgetProduct(entryBudget.getProducts(), dto);
    }

    public void deleteBudget(Long id) {
        BudgetEntity budgetEntity = budgetRepositoy.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id " + id + " não encontrado"));

        BudgetDto dto = budgetEntityToBudgetDto(budgetEntity);

        budgetProductService.deleteProductService(dto);

        budgetRepositoy.delete(budgetEntity);
    }

    public ExitBudgetDto findByIdBudget (Long id) {
        BudgetEntity budgetEntity = budgetRepositoy.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id " + id + " não encontrado"));

        BudgetDto dto = budgetEntityToBudgetDto(budgetEntity);

        Set<EntryBudgetProductDto> entryBudgetProductDtoList = budgetProductService.findByBudget(dto);

        EntryBudgetDto entryBudgetDto = new EntryBudgetDto(
                dto.getClientName(),
                dto.getDate(),
                entryBudgetProductDtoList
        );
        return calculateBudget(entryBudgetDto);
    }

    public List<ExitBudgetDto> findAllBudget () {
        List<BudgetEntity> budgetEntityList = budgetRepositoy.findAll();

        List<ExitBudgetDto> exitBudgetDtoList = new ArrayList<>();

        for (BudgetEntity entity : budgetEntityList) {
            BudgetDto dto = budgetEntityToBudgetDto(entity);

            Set<EntryBudgetProductDto> entryBudgetProductDtoList = budgetProductService.findByBudget(dto);

            EntryBudgetDto entryBudgetDto = new EntryBudgetDto(
                    dto.getClientName(),
                    dto.getDate(),
                    entryBudgetProductDtoList
            );

            exitBudgetDtoList.add(calculateBudget(entryBudgetDto));
        }

        return exitBudgetDtoList;
    }

    private BudgetDto budgetEntityToBudgetDto(BudgetEntity entity) {
        return new BudgetDto(
                entity.getId(),
                entity.getClientName(),
                entity.getDate()
        );
    }
}
