package com.otavio.desafiolike.repository;

import com.otavio.desafiolike.entity.BudgetEntity;
import com.otavio.desafiolike.entity.BudgetProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetProductRepositoy extends JpaRepository<BudgetProductEntity, Long> {
    List<BudgetProductEntity> findByBudget(BudgetEntity budget);
}
