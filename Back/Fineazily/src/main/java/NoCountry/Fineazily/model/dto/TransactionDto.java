package NoCountry.Fineazily.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

public record TransactionDto(Long id,
                             @NotBlank(message = "transaction title must not be blank")
                             String title,
                             String description,
                             @NotNull@PositiveOrZero
                             float amount,
                             LocalDate creationDate
                             ) {
}
