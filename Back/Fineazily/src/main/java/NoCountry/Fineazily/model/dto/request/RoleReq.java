package NoCountry.Fineazily.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record RoleReq(

        @Schema(description = "Unique name of the role", example = "ADMIN", required = true)
        @NotBlank
        String name,

        @Schema(description = "Human-readable name for the role", example = "Administrator", required = true)
        @NotBlank
        String displayName,

        @Schema(description = "Optional description for the role", example = "Has full access to all resources")
        String description
) {

}
