package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public record ExitBudget(
        @NotNull(message = "Campo nome não pode ser nulo")
        @Length(max = 200, message = "Campo nome tem que ter no maximo 200 caracteres")
        @JsonProperty("nomeCliente")
        String clientName,

        @NotNull(message = "Campo data não pode ser nulo")
        @FutureOrPresent(message = "Campo data não pode ser uma data do passado")
        @JsonProperty("data")
        Date date,
        @NotNull(message = "Campo listaProdutos não pode ser nulo")
        @JsonProperty("ListaProdutos")
        Set<ExitBudgetProduct> products,

        @NotNull(message = "Campo totalOrcamento não pode ser nulo")
        @Positive(message = "Campo totalOrcamento tem que ser maior que zero")
        @JsonProperty("totalOrcamento")
        Double TotalBudget
) {
        public ExitBudget {
                products = new HashSet<>();
        }
}