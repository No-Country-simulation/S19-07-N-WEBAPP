package NoCountry.Fineazily.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserDto(
        Long id,
        String name,
        @Email
        String email,
        @Size(min = 8)
        String password) {

}
