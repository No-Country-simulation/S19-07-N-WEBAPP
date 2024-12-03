package NoCountry.Fineazily.model.dto;

import NoCountry.Fineazily.model.enums.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record TransactionDto(Long id,
                             @NotBlank(message = "transaction title must not be blank")
                             String title,
                             String description,
                             @NotNull@PositiveOrZero
                             float amount,
                             @NotBlank
                             TransactionType type
                             ) {
}
