package com.otavio.desafiolike.service;

import com.otavio.desafiolike.dto.EntryBudgetProductDto;
import com.otavio.desafiolike.repository.BudgetProductRepositoy;
import com.otavio.desafiolike.util.BudgetProductUtil;
import com.otavio.desafiolike.util.BudgetUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

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
        doNothing().when(budgetProductRepositoy).deleteAll(BudgetProductUtil.createBudgetProductEntityList());
        when(budgetProductRepositoy.findByBudget(BudgetUtil.createBudgetEntity())).thenReturn(BudgetProductUtil.createBudgetProductEntityList());
    }

    @Test
    public void testSaveBudgetProduct () {
        budgetProductService.saveBudgetProduct(BudgetUtil.createEntryBudgetDto().getProducts(), BudgetUtil.createBudgetDto());

        verify(budgetProductRepositoy, times(1)).save(BudgetProductUtil.createBudgetProductEntityNoId());
    }

    @Test
    public void testDeleteBudgetProduct () {
        budgetProductService.deleteProductService(BudgetUtil.createBudgetDto());
        verify(budgetProductRepositoy, times(1)).deleteAll(any());
    }

    @Test
    public void testFindByBudget () {
        Set<EntryBudgetProductDto> set =  budgetProductService.findByBudget(BudgetUtil.createBudgetDto());
        assertEquals(1, set.size());
    }
}
