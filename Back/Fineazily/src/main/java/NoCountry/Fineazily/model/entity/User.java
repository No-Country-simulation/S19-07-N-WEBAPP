package NoCountry.Fineazily.model.entity;

import NoCountry.Fineazily.model.dto.UserDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @ManyToOne
    private Role rol;

    private LocalDate creationDate;

    @ManyToOne
    private Branch branch;

    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;


}
