package com.otavio.desafiolike.repository;

import com.otavio.desafiolike.entity.BudgetEntity;
import com.otavio.desafiolike.entity.BudgetProductEntity;
import com.otavio.desafiolike.util.BudgetProductUtil;
import com.otavio.desafiolike.util.BudgetUtil;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BudgetRepositoryTest {
    @Autowired
    private BudgetRepositoy budgetRepositoy;

    @Test
    public void testSaveBudget() {
        BudgetEntity entity = BudgetUtil.createBudgetEntity();
        entity.setId(null);

        entity =budgetRepositoy.save(entity);

        assertNotNull(entity.getId());
        assertEquals(1L, entity.getId());
        assertEquals("Cliente", entity.getClientName());
    }

    @Test
    public void testingWhetherTheObjectWasSavedInTheDatabase () {
        save();
        Optional<BudgetEntity> optional = budgetRepositoy.findById(1L);
        assertNotNull(optional);
    }

    private void save() {
        BudgetEntity budgetEntity = BudgetUtil.createBudgetEntity();
        budgetEntity.setId(null);
        budgetRepositoy.save(budgetEntity);
    }

}
