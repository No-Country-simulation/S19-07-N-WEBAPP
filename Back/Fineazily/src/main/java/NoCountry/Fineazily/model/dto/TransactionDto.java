package NoCountry.Fineazily.model.dto;

import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

public record TransactionDto(Long id,
                             String title,
                             String description,
                             @PositiveOrZero(message = "the amount cannot be negative")
                             float amount
                             //@PastOrPresent
                             //LocalDate creationDate
                             ) {
}
