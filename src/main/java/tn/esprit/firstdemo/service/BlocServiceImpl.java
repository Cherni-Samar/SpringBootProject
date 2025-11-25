package tn.esprit.firstdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.firstdemo.entity.Bloc;
import tn.esprit.firstdemo.entity.Chambre;
import tn.esprit.firstdemo.repository.IBlocRepository;
import tn.esprit.firstdemo.repository.IChambreRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlocServiceImpl implements IBlocService {

    @Autowired
    IBlocRepository blocRepository;

    @Autowired
    IChambreRepository chambreRepository;

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

    @Override
    public Bloc affecterChambresABloc(List<Long> numChambre, long idBloc) {
        // Récupérer le Bloc par son ID
        Bloc bloc = blocRepository.findById(idBloc).orElse(null);
        if (bloc == null) {
            throw new RuntimeException("Bloc avec l'ID " + idBloc + " introuvable");
        }

        // Initialiser la liste des chambres si elle est nulle
        if (bloc.getChambres() == null) {
            bloc.setChambres(new ArrayList<>());
        }

        // Pour chaque numéro de chambre
        for (Long numeroChambre : numChambre) {
            // Récupérer la chambre par son numéro
            Chambre chambre = chambreRepository.findByNumeroChambre(numeroChambre);

            if (chambre == null) {
                throw new RuntimeException("Chambre avec le numéro " + numeroChambre + " introuvable");
            }

            // Affecter le Bloc à la Chambre (côté propriétaire ManyToOne)
            chambre.setBloc(bloc);

            // Ajouter la Chambre à la liste des chambres du Bloc (si pas déjà présente)
            if (!bloc.getChambres().contains(chambre)) {
                bloc.getChambres().add(chambre);
            }

            // Sauvegarder la Chambre
            chambreRepository.save(chambre);
        }

        // Retourner le Bloc mis à jour
        return blocRepository.findById(idBloc).orElse(null);
    }

}
