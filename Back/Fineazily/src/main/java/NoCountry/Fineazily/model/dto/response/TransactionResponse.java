package NoCountry.Fineazily.model.dto.response;

import NoCountry.Fineazily.model.enums.MethodType;
import NoCountry.Fineazily.model.enums.MoveType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

public record TransactionResponse(String title,
                                  String description,
                                  @NotNull
                                  @PositiveOrZero
                                  float amount,
                                  LocalDate creationDate,
                                  @NotBlank
                                  MethodType methodType,
                                  @NotBlank
                                  MoveType moveType) {
}
