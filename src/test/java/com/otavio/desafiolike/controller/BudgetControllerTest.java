package com.otavio.desafiolike.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.otavio.desafiolike.dto.EntryBudgetDto;
import com.otavio.desafiolike.service.BudgetService;
import com.otavio.desafiolike.util.BudgetUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.sql.Date;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BudgetController.class)
@EnableWebMvc
public class BudgetControllerTest {
    @MockBean
    private BudgetService budgetService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup () {
         when(budgetService.calculateBudget(BudgetUtil.createEntryBudgetDto())).thenReturn(BudgetUtil.createExitBudgetDto());
         doNothing().when(budgetService).deleteBudget(1L);
         when(budgetService.findByIdBudget(1L)).thenReturn(BudgetUtil.createExitBudgetDto());
    }

    @Test
    public void testCalculateBudget () throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/v1/orcamento/calcular")
                .accept(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(BudgetUtil.createEntryBudgetDto()))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
        verify(budgetService, times(1)).calculateBudget(any());
    }

    @Test
    public void testValidationSchema () throws Exception {
        EntryBudgetDto entryBudgetDto = BudgetUtil.createEntryBudgetDto();
        entryBudgetDto.setClientName(null);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/v1/orcamento/calcular")
                .accept(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(entryBudgetDto))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), response.getStatus());
    }

    @Test
    public void testConfirmBudget () throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/v1/orcamento/confirmar")
                .accept(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(BudgetUtil.createEntryBudgetDto()))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        verify(budgetService, times(1)).saveBudget(any());
    }

    @Test
    public void testDeleteBudgetValidId () throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/v1/orcamento/deletar/1");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        verify(budgetService, times(1)).deleteBudget(1L);
    }

    @Test
    public void findById () throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/orcamento/buscarPorId/1");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        verify(budgetService, times(1)).findByIdBudget(1L);
    }

}
