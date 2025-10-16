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
@Table(name = "etudiant")
public class Etudiant {

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEtudiant;
    private String nomEt;
    private String prenomEt;
    private long cin;
    private String ecole;
    //@Temporal(TemporalType.DATE)
    private Date dateNaissance;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy ="etudiants", cascade = CascadeType.ALL)
    private List<Reservation> reservations;
}

