package NoCountry.Fineazily.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class CashRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "cashRegister", cascade = {CascadeType.MERGE})
    private List<Transaction> transactions;

    @JsonIgnore
    @ManyToOne
    private Branch branch;
}
