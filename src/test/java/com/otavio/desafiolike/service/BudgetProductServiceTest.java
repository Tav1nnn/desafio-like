package com.otavio.desafiolike.service;

import com.otavio.desafiolike.repository.BudgetProductRepositoy;
import com.otavio.desafiolike.util.BudgetProductUtil;
import com.otavio.desafiolike.util.BudgetUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class BudgetProductServiceTest {

    @InjectMocks
    private BudgetProductService budgetProductService;
    @Mock
    private BudgetProductRepositoy budgetProductRepositoy;

    @BeforeEach
    void setup () {
        when(budgetProductRepositoy.save(BudgetProductUtil.createBudgetProductEntityNoId())).thenReturn(BudgetProductUtil.createBudgetProductEntity());
    }

    @Test
    public void testSaveBudgetProduct () {
        budgetProductService.saveBudgetProduct(BudgetUtil.createEntryBudgetDto().getProducts(), BudgetUtil.createBudgetDto());

        verify(budgetProductRepositoy, times(1)).save(BudgetProductUtil.createBudgetProductEntityNoId());
    }
}
