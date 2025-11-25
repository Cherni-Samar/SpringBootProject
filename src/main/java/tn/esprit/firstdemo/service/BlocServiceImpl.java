package tn.esprit.firstdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.firstdemo.entity.Bloc;
import tn.esprit.firstdemo.repository.IBlocRepository;

import java.util.List;

@Service
public class BlocServiceImpl implements IBlocService {

    @Autowired
    IBlocRepository blocRepository;

    @Override
    public List<Bloc> getAllBlocs() {
        return blocRepository.findAll();
    }

    @Override
    public Bloc getBlocById(Long idBloc) {
        return blocRepository.findById(idBloc).orElse(null);
    }

    @Override
    public Bloc addBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public Bloc modifyBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public void removeBloc(Long idBloc) {
        blocRepository.deleteById(idBloc);
    }

    @Override
    public List<Bloc> ListBlocCapacite(){
        return blocRepository.findByCapaciteBlocGreaterThanAndCapaciteBlocLessThan(10L,20L);
    }



    @Override
    public List<Bloc> getBlocsByCapaciteCondition() {
        return List.of();
    }

    @Override
    public List<Bloc> getBlocsByFoyerId(Long idFoyer) {
        return blocRepository.findAllByFoyer_IdFoyer(idFoyer);
    }
    @Override
    public Bloc findBlocByChambreId(Long idChambre) {
        return blocRepository.findBlocByIdChambre(idChambre);
    }

}
