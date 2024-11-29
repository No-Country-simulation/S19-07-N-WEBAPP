package NoCountry.Fineazily.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record RoleRes(

        @Schema(description = "Unique identifier of the role", example = "1")
        Long id,

        @Schema(description = "Human-readable display name of the role", example = "Administrator")
        String name,

        @Schema(description = "Optional description of the role", example = "Has full access to all resources")
        String description
) {

}
