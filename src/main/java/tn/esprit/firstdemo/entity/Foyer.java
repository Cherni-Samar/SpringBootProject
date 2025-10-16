package tn.esprit.firstdemo.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(includeFieldNames = false)
@EqualsAndHashCode()
@Setter

@Entity
@Table(name = "foyer")
public class Foyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Setter(AccessLevel.NONE)
    private long idFoyer;

    private String nomFoyer;

    private long capaciteFoyer;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "foyer")
    @JoinColumn(name = "id_universite")
    private Universite universite;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "foyer", cascade = CascadeType.ALL)
    private List<Bloc> blocs;
}
