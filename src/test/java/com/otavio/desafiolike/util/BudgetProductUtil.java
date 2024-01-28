package com.otavio.desafiolike.util;

import com.otavio.desafiolike.entity.BudgetProductEntity;

public class BudgetProductUtil {
    public static BudgetProductEntity createBudgetProductEntity () {
        return new BudgetProductEntity(1L, "Produto", 20.0, 1, BudgetUtil.createBudgetEntity());
    }
}
