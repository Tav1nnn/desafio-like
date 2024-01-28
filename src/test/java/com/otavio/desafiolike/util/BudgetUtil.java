package com.otavio.desafiolike.util;

import com.otavio.desafiolike.entity.BudgetEntity;

import java.sql.Date;

public class BudgetUtil {

    public static BudgetEntity createBudgetEntity () {
        return new BudgetEntity(1L, "Cliente", new Date(2024,12,25));
    }
}
