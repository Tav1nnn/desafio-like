package com.otavio.desafiolike.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ExitBudget {
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
        private Set<ExitBudgetProduct> products = new HashSet<>();

        @NotNull(message = "Campo totalOrcamento não pode ser nulo")
        @Positive(message = "Campo totalOrcamento tem que ser maior que zero")
        @JsonProperty("totalOrcamento")
        private Double totalBudget;

        public ExitBudget() {
        }

        public ExitBudget(String clientName, Date date, Set<ExitBudgetProduct> products, Double totalBudget) {
                this.clientName = clientName;
                this.date = date;
                this.products = products;
                this.totalBudget = totalBudget;
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

        public Set<ExitBudgetProduct> getProducts() {
                return products;
        }

        public void setProducts(Set<ExitBudgetProduct> products) {
                this.products = products;
        }

        public Double getTotalBudget() {
                return totalBudget;
        }

        public void setTotalBudget(Double totalBudget) {
                this.totalBudget = totalBudget;
        }
}