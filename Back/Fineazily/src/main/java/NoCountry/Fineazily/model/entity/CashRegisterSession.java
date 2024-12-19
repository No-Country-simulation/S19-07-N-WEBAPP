package NoCountry.Fineazily.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class CashRegisterSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime starDateTime;

    private LocalDateTime endDateTime;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Employee employee;

    @ManyToOne(cascade = CascadeType.MERGE)
    private CashRegister cashRegister;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
}
