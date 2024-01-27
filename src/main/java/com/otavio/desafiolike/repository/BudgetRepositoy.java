package com.otavio.desafiolike.repository;

import com.otavio.desafiolike.entity.BudgetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepositoy extends JpaRepository<BudgetEntity, Long> {
}
