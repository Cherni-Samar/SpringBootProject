package tn.esprit.firstdemo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "chambre")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(includeFieldNames = false)
@EqualsAndHashCode()
@Setter
public class Chambre {
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idChambre;
    //@Column(name = "x" , nullable = false , unique = true )

    private long numeroChambre;
    @Enumerated(EnumType.STRING)

    private TypeChambre typeC;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "id_bloc")
    private Bloc bloc;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "chambre", cascade = CascadeType.ALL)
    private List<Reservation> reservations;
}
