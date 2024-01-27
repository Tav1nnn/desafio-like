package repository;

import entity.BudgetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepositoy extends JpaRepository<BudgetEntity, Long> {
}
