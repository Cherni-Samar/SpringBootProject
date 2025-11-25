package tn.esprit.firstdemo.service;

import tn.esprit.firstdemo.entity.Bloc;
import java.util.List;

public interface IBlocService {
    List<Bloc> getAllBlocs();
    Bloc getBlocById(Long idBloc);
    Bloc addBloc(Bloc bloc);
    Bloc modifyBloc(Bloc bloc);
    void removeBloc(Long idBloc);

    List<Bloc> ListBlocCapacite();


    List<Bloc> getBlocsByCapaciteCondition();


    List<Bloc> getBlocsByFoyerId(Long idFoyer);

    Bloc findBlocByChambreId(Long idChambre);
    // Nouvelle méthode
    Bloc affecterChambresABloc(List<Long> numChambre, long idBloc);

}
