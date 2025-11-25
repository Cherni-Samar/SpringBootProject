package tn.esprit.firstdemo.dto;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlocDTO {
    long id;
    String nom;

    long capacite;

//    public static BlocDTO mapper(Bloc bloc){
//       return  BlocDTO.builder()
//                .id(bloc.getIdBloc())
//                .nom(bloc.getNomBloc())
//                .capacite(bloc.getCapaciteBloc())
//                .build();
//    }
}
