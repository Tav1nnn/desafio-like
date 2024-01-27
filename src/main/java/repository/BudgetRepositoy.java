package repository;

import entitiy.BudgetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepositoy extends JpaRepository<BudgetEntity, Long> {
}
