package NoCountry.Fineazily.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public record UserDto(
        @Schema(description = "Unique identifier of the user", example = "1")
        Long id,

        @NotBlank
        @Schema(description = "User's full name", example = "John Doe")
        String name,

        @NotBlank
        @Schema(description = "User's email address", example = "user@example.com")
        @Email
        String email,

        @NotBlank
        @Schema(description = "User's password", example = "P@ssw0rd!", minLength = 8)
        @Size(min = 8, message = "Password must be at least 8 characters long.")
        @Pattern(regexp = "^(?=.*[A-ZÑ])(?=.*[a-zñ])(?=.*\\d)(?=.*[-@#$%^&*.,()_+{}|;:'\"<>/!¡¿?])[A-ZÑa-zñ\\d-@#$%^&*.,()_+{}|;:'\"<>/!¡¿?]{8,}$",
                message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character.")
        String password) {

}
