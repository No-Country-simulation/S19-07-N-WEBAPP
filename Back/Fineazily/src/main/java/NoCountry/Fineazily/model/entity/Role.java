package NoCountry.Fineazily.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String displayName;

    private String description;

    private int hierarchyLevel;

    private boolean status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToMany
    private  List<Permissions> permissions;

    @OneToMany(mappedBy = "role")
    private List<User> users;
}
