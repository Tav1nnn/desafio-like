package com.otavio.desafiolike.service;

import com.otavio.desafiolike.dto.ExitBudgetDto;
import com.otavio.desafiolike.repository.BudgetRepositoy;
import com.otavio.desafiolike.util.BudgetUtil;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class BudgetServiceTest {

    @InjectMocks
    private BudgetService budgetService;

    @Mock
    private BudgetRepositoy budgetRepositoy;

    @Mock
    private BudgetProductService budgetProductService;

    @BeforeEach
    void setup () {
        when(budgetRepositoy.save(BudgetUtil.createBudgetEntityNoId())).thenReturn(BudgetUtil.createBudgetEntity());
        doNothing().when(budgetProductService).saveBudgetProduct(BudgetUtil.createEntryBudgetDto().getProducts(), BudgetUtil.createBudgetDto());
    }

    @Test
    public void testCalculateBudget () {
        ExitBudgetDto expectedBudget = budgetService.calculateBudget(BudgetUtil.createEntryBudgetDto());

        ExitBudgetDto exitBudgetDto = BudgetUtil.createExitBudgetDto();

        assertEquals(exitBudgetDto.getClientName(), expectedBudget.getClientName());
        assertEquals(exitBudgetDto.getDate(), expectedBudget.getDate());
        assertEquals(exitBudgetDto.getTotalBudget(), expectedBudget.getTotalBudget());
        assertEquals(exitBudgetDto.getProducts().size(), expectedBudget.getProducts().size());
    }

    @Test
    public void testSaveBudget () {
        budgetService.saveBudget(BudgetUtil.createEntryBudgetDto());
        verify(budgetProductService, times(1)).saveBudgetProduct(any(), any());
        verify(budgetRepositoy, times(1)).save(BudgetUtil.createBudgetEntityNoId());
    }
}
