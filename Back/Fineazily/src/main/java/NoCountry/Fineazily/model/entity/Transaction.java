package NoCountry.Fineazily.model.entity;

import NoCountry.Fineazily.model.enums.MethodType;
import NoCountry.Fineazily.model.enums.MoveType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private float amount;

    private LocalDate creationDate;

    @Enumerated(value = EnumType.STRING)
    private MethodType methodType;

    @Enumerated(value = EnumType.STRING)
    private MoveType moveType;

    @ManyToMany
    private List<Tag> tags;

    @ManyToOne
    private CashRegisterSession session;
}
