package NoCountry.Fineazily.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    private String name;

    private String phone;

    private String address;

    @OneToMany(mappedBy = "branch")
    private List<User> users;

    @OneToMany(mappedBy = "branch")
    private List<Box> boxes;

}
