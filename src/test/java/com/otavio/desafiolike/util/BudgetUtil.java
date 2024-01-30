package com.otavio.desafiolike.util;

import com.otavio.desafiolike.dto.BudgetDto;
import com.otavio.desafiolike.dto.EntryBudgetDto;
import com.otavio.desafiolike.dto.ExitBudgetDto;
import com.otavio.desafiolike.entity.BudgetEntity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class BudgetUtil {

    public static BudgetEntity createBudgetEntity () {
        return new BudgetEntity(1L, "Cliente", new Date(2024,12,25));
    }
    public static BudgetEntity createBudgetEntityNoId () {
       BudgetEntity budgetEntity = new BudgetEntity(1L, "Cliente", new Date(2024,12,25));
       budgetEntity.setId(null);
       return budgetEntity;
    }
    public static BudgetDto createBudgetDto () {
        return new BudgetDto(1L, "Cliente", new Date(2024,12,25));
    }

    public static EntryBudgetDto createEntryBudgetDto () {
        EntryBudgetDto entryBudgetDto = new EntryBudgetDto();
        entryBudgetDto.setClientName("Cliente");
        entryBudgetDto.setDate(new Date(2024,12,25));
        entryBudgetDto.getProducts().add(BudgetProductUtil.createEntryBudgetProductDto());

        return entryBudgetDto;
    }

    public static ExitBudgetDto createExitBudgetDto () {
        ExitBudgetDto exitBudgetDto = new ExitBudgetDto();
        exitBudgetDto.setClientName("Cliente");
        exitBudgetDto.setDate(new Date(2024,12,25));
        exitBudgetDto.getProducts().add(BudgetProductUtil.createExitBudgetProductDto());
        exitBudgetDto.setTotalBudget(20.0);
        return exitBudgetDto;
    }

    public static List<BudgetEntity> createExitBudgetEntityList () {
        List<BudgetEntity> list = new ArrayList<>();

        list.add(createBudgetEntity());
        return list;
    }
}
