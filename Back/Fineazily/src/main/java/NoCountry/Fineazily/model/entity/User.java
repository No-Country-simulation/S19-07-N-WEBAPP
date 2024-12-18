package NoCountry.Fineazily.model.entity;

import NoCountry.Fineazily.model.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(unique = true)
    private String email;

    private String passwordHash;

    private LocalDate creationDate;

    private LocalDateTime lastLogin;

    private boolean isActive;

    private boolean emailVerified;

    @ManyToOne
    private Role role;

    @ManyToMany
    private List<Permissions> permissions;

    @OneToOne
    private UserProfiles profile;
}
