package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public record EntryBudgetProduct(

        @NotNull(message = "Campo nome não pode ser nulo")
        @Length(max = 200, message = "Campo nome tem que ter no maximo 200 caracteres")
        @JsonProperty("nome")
        String productName,
        @NotNull(message = "Campo valor não pode ser nulo")
        @Positive(message = "Valor tem que ser maior que zero")
        @JsonProperty("valor")
        Double productValue,
        @NotNull(message = "Campo quantidade não pode ser nulo")
        @Positive(message = "Valor tem que ser maior que zero")
        @JsonProperty("quantidade")
        Integer quantity
) {
}
