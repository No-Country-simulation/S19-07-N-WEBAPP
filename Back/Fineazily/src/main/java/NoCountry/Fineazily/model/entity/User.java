package NoCountry.Fineazily.model.entity;

import NoCountry.Fineazily.model.dto.UserDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    @ManyToOne
    private Role rol;

    private LocalDate creationDate;

    public User(UserDto dto) {
        this.name = dto.name();
        this.email = dto.email();
        this.password = dto.password();
    }
}
