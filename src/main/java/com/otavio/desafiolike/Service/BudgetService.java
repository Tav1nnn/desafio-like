package com.otavio.desafiolike.Service;

import com.otavio.desafiolike.dto.EntryBudget;
import com.otavio.desafiolike.dto.EntryBudgetProduct;
import com.otavio.desafiolike.dto.ExitBudget;
import com.otavio.desafiolike.dto.ExitBudgetProduct;
import org.springframework.stereotype.Service;
import com.otavio.desafiolike.repository.BudgetRepositoy;

import java.util.HashSet;
import java.util.Set;

@Service
public class BudgetService {

    private final BudgetRepositoy budgetRepositoy;

    public BudgetService(BudgetRepositoy budgetRepositoy) {
        this.budgetRepositoy = budgetRepositoy;
    }

    public ExitBudget calculateBudget(EntryBudget entryBudget) {
        Set<ExitBudgetProduct> exitBudgetProductSet = new HashSet<>();
        double totalBudget = 0.0;

        for(EntryBudgetProduct product : entryBudget.getProducts()) {
            double totalValue = product.getQuantity() * product.getProductValue();
            totalBudget += totalValue;
            ExitBudgetProduct exitProduct = new ExitBudgetProduct(
                    product.getProductName(),
                    product.getProductValue(),
                    product.getQuantity(),
                    totalValue
            );
            exitBudgetProductSet.add(exitProduct);
        }

        return new ExitBudget(
                entryBudget.getClientName(),
                entryBudget.getDate(),
                exitBudgetProductSet,
                totalBudget
        );
    }
}
