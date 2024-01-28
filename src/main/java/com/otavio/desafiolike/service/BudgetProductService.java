package com.otavio.desafiolike.service;

import com.otavio.desafiolike.dto.BudgetDto;
import com.otavio.desafiolike.dto.EntryBudgetProductDto;
import com.otavio.desafiolike.entity.BudgetEntity;
import com.otavio.desafiolike.entity.BudgetProductEntity;
import com.otavio.desafiolike.repository.BudgetProductRepositoy;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class BudgetProductService {

    private final BudgetProductRepositoy repositoy;

    public BudgetProductService(BudgetProductRepositoy repositoy) {
        this.repositoy = repositoy;
    }

    protected void saveBudgetProduct(Set<EntryBudgetProductDto> set, BudgetDto budgetDto) {
        BudgetEntity budgetEntity = new BudgetEntity(
          budgetDto.getId(),
          budgetDto.getClientName(),
          budgetDto.getDate()
        );

        for (EntryBudgetProductDto dto : set) {
            BudgetProductEntity entity = new BudgetProductEntity();
            entity.setProductName(dto.getProductName());
            entity.setProductValue(dto.getProductValue());
            entity.setQuantity(dto.getQuantity());
            entity.setBudget(budgetEntity);

            repositoy.save(entity);
        }
    }
}
