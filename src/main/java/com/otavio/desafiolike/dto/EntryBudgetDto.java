package com.otavio.desafiolike.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;


import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class EntryBudgetDto {
        @NotNull(message = "Campo nome não pode ser nulo")
        @Length(max = 200, message = "Campo nome tem que ter no maximo 200 caracteres")
        @JsonProperty("nomeCliente")
        private String clientName;

        @NotNull(message = "Campo data não pode ser nulo")
        @FutureOrPresent(message = "Campo data não pode ser uma data do passado")
        @JsonProperty("data")
        private Date date;

        @NotNull(message = "Campo listaProdutos não pode ser nulo")
        @JsonProperty("ListaProdutos")
        @Valid
        private Set<EntryBudgetProductDto> products = new HashSet<>();

        public EntryBudgetDto() {

        }

        public EntryBudgetDto(String clientName, Date date, Set<EntryBudgetProductDto> products) {
                this.clientName = clientName;
                this.date = date;
                this.products = products;
        }

        public String getClientName() {
                return clientName;
        }

        public void setClientName(String clientName) {
                this.clientName = clientName;
        }

        public Date getDate() {
                return date;
        }

        public void setDate(Date date) {
                this.date = date;
        }

        public Set<EntryBudgetProductDto> getProducts() {
                return products;
        }

        public void setProducts(Set<EntryBudgetProductDto> products) {
                this.products = products;
        }
}