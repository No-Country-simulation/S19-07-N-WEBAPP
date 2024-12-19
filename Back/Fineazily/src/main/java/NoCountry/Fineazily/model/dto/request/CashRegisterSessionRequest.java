package NoCountry.Fineazily.model.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CashRegisterSessionRequest(LocalDateTime startedDate,
                                         @NotNull(message = "employee id must not be null")
                                         Long employeeId,
                                         @NotNull(message = "cash register id must not be null")
                                         Long cashRegisterId) {

}
