package com.otavio.desafiolike.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public class EntryBudgetProductDto {
        @NotNull(message = "Campo nome não pode ser nulo")
        @Length(max = 200, message = "Campo nome tem que ter no maximo 200 caracteres")
        @JsonProperty("nome")
        private String productName;

        @NotNull(message = "Campo valor não pode ser nulo")
        @Positive(message = "Valor tem que ser maior que zero")
        @JsonProperty("valor")
        private Double productValue;

        @NotNull(message = "Campo quantidade não pode ser nulo")
        @Positive(message = "Valor tem que ser maior que zero")
        @JsonProperty("quantidade")
        private Integer quantity;

        public EntryBudgetProductDto() {
        }

        public EntryBudgetProductDto(String productName, Double productValue, Integer quantity) {
                this.productName = productName;
                this.productValue = productValue;
                this.quantity = quantity;
        }

        public String getProductName() {
                return productName;
        }

        public void setProductName(String productName) {
                this.productName = productName;
        }

        public Double getProductValue() {
                return productValue;
        }

        public void setProductValue(Double productValue) {
                this.productValue = productValue;
        }

        public Integer getQuantity() {
                return quantity;
        }

        public void setQuantity(Integer quantity) {
                this.quantity = quantity;
        }
}