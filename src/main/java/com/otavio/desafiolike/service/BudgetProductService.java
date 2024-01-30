package com.otavio.desafiolike.service;

import com.otavio.desafiolike.dto.BudgetDto;
import com.otavio.desafiolike.dto.EntryBudgetProductDto;
import com.otavio.desafiolike.entity.BudgetEntity;
import com.otavio.desafiolike.entity.BudgetProductEntity;
import com.otavio.desafiolike.repository.BudgetProductRepositoy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class BudgetProductService {

    private final BudgetProductRepositoy budgetProductRepositoy;

    public BudgetProductService(BudgetProductRepositoy repositoy) {
        this.budgetProductRepositoy = repositoy;
    }

    protected void saveBudgetProduct(Set<EntryBudgetProductDto> set, BudgetDto budgetDto) {
        BudgetEntity budgetEntity = budgetDtoToBudgetEntity(budgetDto);

        for (EntryBudgetProductDto dto : set) {
            BudgetProductEntity entity = new BudgetProductEntity();
            entity.setProductName(dto.getProductName());
            entity.setProductValue(dto.getProductValue());
            entity.setQuantity(dto.getQuantity());
            entity.setBudget(budgetEntity);

            budgetProductRepositoy.save(entity);
        }
    }

    protected void deleteProductService(BudgetDto dto) {
        BudgetEntity budgetEntity = budgetDtoToBudgetEntity(dto);

        List<BudgetProductEntity> budgetProductEntityList = budgetProductRepositoy.findByBudget(budgetEntity);

        budgetProductRepositoy.deleteAll(budgetProductEntityList);
    }

    protected Set<EntryBudgetProductDto> findByBudget(BudgetDto dto) {
        BudgetEntity budgetEntity = budgetDtoToBudgetEntity(dto);
        List<BudgetProductEntity> budgetProductEntityList = budgetProductRepositoy.findByBudget(budgetEntity);

        Set<EntryBudgetProductDto> entryBudgetProductDtoList = new HashSet<>();

        for (BudgetProductEntity entity : budgetProductEntityList) {
            EntryBudgetProductDto entryBudgetProductDto = new EntryBudgetProductDto(
                    entity.getProductName(),
                    entity.getProductValue(),
                    entity.getQuantity()
            );

            entryBudgetProductDtoList.add(entryBudgetProductDto);
        }

        return entryBudgetProductDtoList;
    }

    private BudgetEntity budgetDtoToBudgetEntity(BudgetDto budgetDto) {
        return new BudgetEntity(
                budgetDto.getId(),
                budgetDto.getClientName(),
                budgetDto.getDate()
        );
    }


}
