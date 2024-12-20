package NoCountry.Fineazily.model.dto.response;

import java.time.LocalDateTime;

public record CashRegisterResponse(
        String name,
        boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
