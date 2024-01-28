package com.otavio.desafiolike.repository;

import com.otavio.desafiolike.entity.BudgetEntity;
import com.otavio.desafiolike.util.BudgetUtil;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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

}
