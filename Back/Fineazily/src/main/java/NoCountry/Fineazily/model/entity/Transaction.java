package NoCountry.Fineazily.model.entity;

import NoCountry.Fineazily.model.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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

    @ManyToOne
    private MethodType methodType;

    @ManyToOne
    private Box box;

    @ManyToOne
    private User user;

    @Enumerated(value = EnumType.STRING)
    private TransactionType type;

}
