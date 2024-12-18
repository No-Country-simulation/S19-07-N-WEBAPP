package NoCountry.Fineazily.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class CashRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean isActive;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne
    private Area area;

    @OneToMany(mappedBy = "cashRegister")
    private List<CashRegisterSession> sessions;
}
