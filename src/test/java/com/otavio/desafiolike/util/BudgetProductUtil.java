package com.otavio.desafiolike.util;

import com.otavio.desafiolike.dto.EntryBudgetProductDto;
import com.otavio.desafiolike.dto.ExitBudgetProductDto;
import com.otavio.desafiolike.entity.BudgetProductEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BudgetProductUtil {
    public static BudgetProductEntity createBudgetProductEntity () {
        return new BudgetProductEntity(1L, "Produto", 20.0, 1, BudgetUtil.createBudgetEntity());
    }
    public static BudgetProductEntity createBudgetProductEntityNoId () {
        BudgetProductEntity budgetProductEntity = new BudgetProductEntity(1L, "Produto", 20.0, 1, BudgetUtil.createBudgetEntity());
        budgetProductEntity.setId(null);
        return budgetProductEntity;
    }

    public static EntryBudgetProductDto createEntryBudgetProductDto () {
        return new EntryBudgetProductDto("Produto", 20.0, 1);
    }

    public static ExitBudgetProductDto createExitBudgetProductDto () {
        return new ExitBudgetProductDto("Produto", 20.0, 1, 20.0);
    }

    public static Set<EntryBudgetProductDto> createEntryBudgetProductDtoSet () {
        Set<EntryBudgetProductDto> set = new HashSet<>();

        set.add(createEntryBudgetProductDto());

        return set;
    }

    public static List<BudgetProductEntity> createBudgetProductEntityList () {
        List<BudgetProductEntity> list = new ArrayList<>();

        list.add(createBudgetProductEntity());

        return list;
    }

}
