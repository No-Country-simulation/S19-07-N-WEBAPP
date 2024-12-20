package NoCountry.Fineazily.model.dto.request;

import java.time.LocalDateTime;

public record CashRegisterRequest(
        String name,
        boolean isActive,
        LocalDateTime createdAt) {
}
