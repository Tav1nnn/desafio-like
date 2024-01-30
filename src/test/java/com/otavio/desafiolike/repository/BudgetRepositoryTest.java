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

import java.util.List;
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
    public void testFindById () {
        save();
        Optional<BudgetEntity> optional = budgetRepositoy.findById(1L);
        assertNotNull(optional);
    }

    @Test
    public void testFindAll () {
        save();

        List<BudgetEntity> list = budgetRepositoy.findAll();

        assertEquals(1, list.size());
    }

    @Test
    public void testFindByIdBudget () {
        save();
        budgetRepositoy.delete(BudgetUtil.createBudgetEntity());

        boolean exist = budgetRepositoy.existsById(1L);

        assertFalse(exist);
    }

    private void save() {
        BudgetEntity budgetEntity = BudgetUtil.createBudgetEntity();
        budgetEntity.setId(null);
        budgetRepositoy.save(budgetEntity);
    }

}
