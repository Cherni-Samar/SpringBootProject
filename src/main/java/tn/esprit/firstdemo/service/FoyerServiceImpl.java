package tn.esprit.firstdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.firstdemo.entity.Bloc;
import tn.esprit.firstdemo.entity.Foyer;
import tn.esprit.firstdemo.entity.Universite;
import tn.esprit.firstdemo.repository.IFoyerRepository;
import tn.esprit.firstdemo.repository.IUniversiteRepository;

import java.util.List;

@Service
public class FoyerServiceImpl implements IFoyerService {

    @Autowired
    IFoyerRepository foyerRepository;
    @Autowired
    IUniversiteRepository universiteRepository;

    @Override
    public List<Foyer> getAllFoyers() {
        return foyerRepository.findAll();
    }

    @Override
    public Foyer getFoyerById(Long idFoyer) {
        return foyerRepository.findById(idFoyer).orElse(null);
    }

    @Override
    public Foyer addFoyer(Foyer foyer) {
        return foyerRepository.save(foyer);
    }

    @Override
    public Foyer modifyFoyer(Foyer foyer) {
        return foyerRepository.save(foyer);
    }

    @Override
    public void removeFoyer(Long idFoyer) {
        foyerRepository.deleteById(idFoyer);
    }
    @Override
    public Foyer ajouterFoyerEtAffecterAUniversite(Foyer foyer, long idUniversite) {
        // Récupérer l'Université par son ID
        Universite universite = universiteRepository.findById(idUniversite).orElse(null);
        if (universite == null) {
            throw new RuntimeException("Université avec l'ID " + idUniversite + " introuvable");
        }

        // Si le Foyer a des Blocs, établir la relation bidirectionnelle
        if (foyer.getBlocs() != null && !foyer.getBlocs().isEmpty()) {
            for (Bloc bloc : foyer.getBlocs()) {
                bloc.setFoyer(foyer);  // Associer chaque Bloc au Foyer
            }
        }

        // Sauvegarder le Foyer avec ses Blocs (cascade ALL le fera automatiquement)
        Foyer savedFoyer = foyerRepository.save(foyer);

        // Affecter le Foyer à l'Université
        universite.setFoyer(savedFoyer);
        savedFoyer.setUniversite(universite);

        // Sauvegarder l'Université avec le Foyer affecté
        universiteRepository.save(universite);

        return savedFoyer;
    }
}
