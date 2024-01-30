package com.otavio.desafiolike.repository;

import com.otavio.desafiolike.entity.BudgetEntity;
import com.otavio.desafiolike.entity.BudgetProductEntity;
import com.otavio.desafiolike.util.BudgetProductUtil;
import com.otavio.desafiolike.util.BudgetUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BudgetProductRepositoryTest {
    @Autowired
    private BudgetProductRepositoy budgetProductRepositoy;
    @Autowired
    private BudgetRepositoy budgetRepositoy;

    @Test
    public void testSaveBudgetProduct() {
        budgetRepositoy.save(BudgetUtil.createBudgetEntity());
        BudgetProductEntity entity = BudgetProductUtil.createBudgetProductEntity();
        entity.setId(null);

        entity = budgetProductRepositoy.save(entity);

        assertNotNull(entity.getId());
        assertEquals(1L, entity.getId());
        assertEquals("Produto", entity.getProductName());
    }

    @Test
    public void testFindById () {
        save();
        Optional<BudgetProductEntity> optional = budgetProductRepositoy.findById(1L);
        assertNotNull(optional);
    }

    @Test
    public void testFindByBudget () {
        save();
        List<BudgetProductEntity> list = budgetProductRepositoy.findByBudget(BudgetUtil.createBudgetEntity());

        assertEquals(1, list.size());
    }

    @Test
    public void testDelete () {
        save();
        budgetProductRepositoy.delete(BudgetProductUtil.createBudgetProductEntity());

        boolean exist = budgetProductRepositoy.existsById(1L);

        assertFalse(exist);
    }

    private void save() {
        BudgetEntity budgetEntity = BudgetUtil.createBudgetEntity();
        budgetEntity.setId(null);
        BudgetProductEntity budgetProductEntity = BudgetProductUtil.createBudgetProductEntity();
        budgetProductEntity.setId(null);
        budgetRepositoy.save(budgetEntity);
        budgetProductRepositoy.save(budgetProductEntity);
    }

}
