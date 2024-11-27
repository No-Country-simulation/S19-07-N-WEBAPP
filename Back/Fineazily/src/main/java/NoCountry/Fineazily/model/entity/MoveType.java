package NoCountry.Fineazily.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.mapping.Set;

import java.util.List;

@Entity
@Getter
@Setter
public class MoveType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @OneToMany(mappedBy = "moveType")
    private List<Transaction> transactions ;
}
