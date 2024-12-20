package NoCountry.Fineazily.model.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record EmployeeRequest(
        @NotNull(message = "area id must not be null")
        Long areaId,
        LocalDate startDate,
        LocalDateTime createdAt
) {

}
