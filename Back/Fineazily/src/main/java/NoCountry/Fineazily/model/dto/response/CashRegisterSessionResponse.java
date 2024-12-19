package NoCountry.Fineazily.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

public record CashRegisterSessionResponse(
        @Schema(description = "timeStamp from the session start")
        LocalDateTime startedDate,
        @Schema(description = "timeStamp from the end of the session")
        LocalDateTime endDateTime,
        @Schema(description = "the id of the employee who started the session")
        Long employeeId,
        @Schema(description = "the id of the cash register where the session started")
        Long cashRegisterId) {
}
