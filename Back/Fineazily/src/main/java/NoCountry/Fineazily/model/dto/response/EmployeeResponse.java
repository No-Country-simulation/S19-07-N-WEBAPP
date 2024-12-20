package NoCountry.Fineazily.model.dto.response;


import java.time.LocalDate;
import java.time.LocalDateTime;


public record EmployeeResponse(Long id,
                               LocalDate startDate,
                               LocalDateTime createdAt,
                               LocalDateTime updatedAt,
                               Long areaId) {
}
