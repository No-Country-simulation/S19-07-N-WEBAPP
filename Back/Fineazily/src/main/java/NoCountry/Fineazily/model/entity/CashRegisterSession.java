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

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private CashRegister cashRegister;

    @OneToMany(mappedBy = "session")
    private List<Transaction> transactions;
}
