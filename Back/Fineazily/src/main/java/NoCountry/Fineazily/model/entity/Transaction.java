package NoCountry.Fineazily.model.entity;

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

    @ManyToOne(cascade = CascadeType.MERGE)
    private MethodType methodType;

    @ManyToOne(cascade = CascadeType.MERGE)
    private MoveType moveType;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Box box;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;

}
