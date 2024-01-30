package com.otavio.desafiolike.service;

import com.otavio.desafiolike.dto.ExitBudgetDto;
import com.otavio.desafiolike.repository.BudgetRepositoy;
import com.otavio.desafiolike.service.exception.ResourceNotFoundException;
import com.otavio.desafiolike.util.BudgetProductUtil;
import com.otavio.desafiolike.util.BudgetUtil;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

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
        when(budgetRepositoy.findById(1L)).thenReturn(Optional.of(BudgetUtil.createBudgetEntity()));
        when(budgetRepositoy.findById(2L)).thenThrow(ResourceNotFoundException.class);
        doNothing().when(budgetRepositoy).delete(BudgetUtil.createBudgetEntity());
        when(budgetRepositoy.findAll()).thenReturn(BudgetUtil.createExitBudgetEntityList());
        doNothing().when(budgetProductService).saveBudgetProduct(BudgetUtil.createEntryBudgetDto().getProducts(), BudgetUtil.createBudgetDto());
        doNothing().when(budgetProductService).deleteProductService(BudgetUtil.createBudgetDto());
        when(budgetProductService.findByBudget(BudgetUtil.createBudgetDto())).thenReturn(BudgetProductUtil.createEntryBudgetProductDtoSet());

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

    @Test
    public void testDeleteBudgetValidId () {
        budgetService.deleteBudget(1L);
        verify(budgetRepositoy, times(1)).findById(any());
        verify(budgetProductService, times(1)).deleteProductService(any());
        verify(budgetRepositoy, times(1)).delete(any());
    }

    @Test
    public void testDeleteBudgetInvalidId () {
        assertThrows(ResourceNotFoundException.class, () -> budgetService.deleteBudget(2L));
    }

    @Test
    public void testFindByIdBudget () {
        ExitBudgetDto dto = budgetService.findByIdBudget(1L);
        assertNotNull(dto);
        verify(budgetRepositoy, times(1)).findById(any());
    }

    @Test
    public void testFindByBudgetInvalidId () {
        assertThrows(ResourceNotFoundException.class, () -> budgetService.findByIdBudget(2L));
    }

    @Test
    public void testFindAllBudget () {
        List<ExitBudgetDto> list = budgetService.findAllBudget();

        assertEquals(1, list.size());
    }
}
