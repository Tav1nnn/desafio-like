package com.otavio.desafiolike.Service;

import com.otavio.desafiolike.dto.*;
import com.otavio.desafiolike.entity.BudgetEntity;
import org.springframework.stereotype.Service;
import com.otavio.desafiolike.repository.BudgetRepositoy;

import java.util.HashSet;
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

       BudgetDto dto = new BudgetDto(
               entity.getId(),
               entity.getClientName(),
               entity.getDate()
       );

       budgetProductService.saveBudgetProductService(entryBudget.getProducts(), dto);
    }
}
