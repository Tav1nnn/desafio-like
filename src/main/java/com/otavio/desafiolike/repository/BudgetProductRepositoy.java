package repository;

import entity.BudgetEntity;
import entity.BudgetProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetProductRepositoy extends JpaRepository<BudgetProductEntity, Long> {
    List<BudgetProductEntity> findByBudget(BudgetEntity budget);
}
