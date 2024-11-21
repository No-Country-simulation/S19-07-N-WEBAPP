package NoCountry.Fineazily.model.dto;

import jakarta.validation.constraints.Email;

public record UserDto(String name,
                      @Email
                      String email,
                      String password) {
}
