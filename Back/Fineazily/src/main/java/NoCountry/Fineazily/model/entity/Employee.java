package NoCountry.Fineazily.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private boolean isActive;

    @OneToMany(mappedBy = "employee")
    private List<CashRegisterSession> sessions;

    @ManyToOne
    private Area area;

    @OneToOne
    private UserProfiles profile;
}
