package tn.esprit.firstdemo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(includeFieldNames = false)
@EqualsAndHashCode()
@Setter
@Entity
@Table(name = "reservation")
public class Reservation {

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idReservation;
    //@Temporal(TemporalType.TIMESTAMP)
    private Date anneeUniversitaire;
    private boolean estValide;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "id_chambre")
    private Chambre chambre;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Etudiant> etudiants;
}

