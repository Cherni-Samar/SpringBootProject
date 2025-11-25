package tn.esprit.firstdemo.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.firstdemo.entity.Foyer;
import tn.esprit.firstdemo.entity.Universite;
import tn.esprit.firstdemo.repository.IFoyerRepository;
import tn.esprit.firstdemo.repository.IUniversiteRepository;

import java.util.List;

@Service
public class UniversiteServiceImpl implements IUniversiteService {
    @Autowired
    IUniversiteRepository universiteRepository;

    @Autowired
    IFoyerRepository foyerRepository;



    @Override
    public List<Universite> getAllUniversites() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite getUniversiteById(Long idUniversite) {
        return universiteRepository.findById(idUniversite).orElse(null);
    }

    @Override
    public Universite addUniversite(Universite universite) {
        return universiteRepository.save(universite);
    }

    @Override
    public Universite modifyUniversite(Universite universite) {
        return universiteRepository.save(universite);
    }

    @Override
    public void removeUniversite(Long idUniversite) {
        universiteRepository.deleteById(idUniversite);
    }

    @Override
    public Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite) {
        // Récupérer le Foyer par son ID
        Foyer foyer = foyerRepository.findById(idFoyer).orElse(null);
        if (foyer == null) {
            throw new RuntimeException("Foyer avec l'ID " + idFoyer + " introuvable");
        }

        // Récupérer l'Université par son nom
        Universite universite = universiteRepository.findByNomUniversite(nomUniversite);
        if (universite == null) {
            throw new RuntimeException("Université avec le nom " + nomUniversite + " introuvable");
        }

        // Affecter le Foyer à l'Université (côté propriétaire de la relation)
        universite.setFoyer(foyer);

        // Affecter l'Université au Foyer (côté inverse pour maintenir la cohérence bidirectionnelle)
        foyer.setUniversite(universite);

        // Sauvegarder l'Université
        return universiteRepository.save(universite);
    }

    @Override
    public Universite desaffecterFoyerAUniversite(long idUniversite) {
        // Récupérer l'Université par son ID
        Universite universite = universiteRepository.findById(idUniversite).orElse(null);
        if (universite == null) {
            throw new RuntimeException("Université avec l'ID " + idUniversite + " introuvable");
        }

        // Récupérer le Foyer associé à l'Université
        Foyer foyer = universite.getFoyer();

        // Si l'université a un foyer affecté, on le désaffecte
        if (foyer != null) {
            // Désaffecter le côté inverse (Foyer -> Universite)
            foyer.setUniversite(null);

            // Désaffecter le côté propriétaire (Universite -> Foyer)
            universite.setFoyer(null);

            // Sauvegarder les modifications
            foyerRepository.save(foyer);
        }

        // Sauvegarder et retourner l'université mise à jour
        return universiteRepository.save(universite);
    }
}
