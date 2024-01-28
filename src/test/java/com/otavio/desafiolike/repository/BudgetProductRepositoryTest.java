package com.otavio.desafiolike.repository;

import com.otavio.desafiolike.entity.BudgetEntity;
import com.otavio.desafiolike.entity.BudgetProductEntity;
import com.otavio.desafiolike.util.BudgetProductUtil;
import com.otavio.desafiolike.util.BudgetUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    public void testingWhetherTheObjectWasSavedInTheDatabase () {

    }

}
