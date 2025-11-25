package tn.esprit.firstdemo.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;
@Entity
@Table(name = "bloc")
@Getter
@Setter
@ToString
@EqualsAndHashCode()
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bloc {
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     long idBloc;

     String nomBloc;

     public long capaciteBloc;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "id_foyer")
    Foyer foyer;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "bloc", cascade = CascadeType.ALL)
    @JsonIgnore
    List<Chambre> chambres;
}
