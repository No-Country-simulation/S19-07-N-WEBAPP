package NoCountry.Fineazily.model.entity;

import jakarta.persistence.*;
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

    private String email;

    private String password;

    private LocalDate creationDate;

    @ManyToOne
    private Role rol;

    @ManyToOne
    private Branch branch;

    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;



}
