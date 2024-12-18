package NoCountry.Fineazily.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class UserProfiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String address;

    //private Blob photo;

    private LocalDate startedAt;

    private boolean isActive;

    @OneToOne(mappedBy = "profile")
    private User user;

    @OneToOne(mappedBy = "manager")
    private Branch branch;

    @OneToOne(mappedBy = "profile")
    private Employee employee;
}
