package tn.esprit.firstdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.JdbcAggregateOperations;
import org.springframework.stereotype.Service;
import tn.esprit.firstdemo.dto.ChambreDTO;
import tn.esprit.firstdemo.entity.*;
import tn.esprit.firstdemo.mapper.ChambreMapper;
import tn.esprit.firstdemo.repository.IBlocRepository;
import tn.esprit.firstdemo.repository.IChambreRepository;
import tn.esprit.firstdemo.repository.IFoyerRepository;
import tn.esprit.firstdemo.repository.IUniversiteRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChambreServiceImpl implements IChambreService {
    @Autowired
    IChambreRepository chambreRepository;

    @Autowired
    IBlocRepository blocRepository;

    @Autowired
    private ChambreMapper chambreMapper;

    @Autowired
    private IFoyerRepository foyerRepository;

    @Autowired
    private IUniversiteRepository universiteRepository;


    @Override
    public List<Chambre> retrieveAllChambres() {
        return chambreRepository.findAll();
    }
    public Chambre retrieveChambre(Long chambreId) {
        return chambreRepository.findById(chambreId).get();
    }
    public Chambre addChambre(Chambre c) {
        return chambreRepository.save(c);
    }
    public void removeChambre(Long chambreId) {
        chambreRepository.deleteById(chambreId);
    }
    public Chambre modifyChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @Override
    public ChambreDTO assignBlocToChambre(Long idChambre, Long idBloc) {
        if (blocRepository.existsById(idBloc) && chambreRepository.existsById(idChambre)) {
            // Récupération des entités
            Chambre chambre = chambreRepository.findById(idChambre).get();
            Bloc bloc = blocRepository.findById(idBloc).get();

            //  On set le bloc
            chambre.setBloc(bloc);

            // Save updated chambre
            chambre = chambreRepository.save(chambre);

            //  Mapping automatique entity -> DTO
            return chambreMapper.toDto(chambre);

            /*
            //  Ancien mapping manuel :
            return ChambreDTO.mapper(chambre);
            */
        } else {
            return null;
        }
    }

    @Override
    public  Bloc addBlocAndChambre(Bloc bloc){
        return blocRepository.save(bloc);
    }

    @Override
    public Chambre findChambreByNumero(Long id){
        // Ici on peut aussi utiliser le mapping automatique si findByNumeroChambre retourne des entities
        // return chambreRepository.findByNumeroChambre(id)
        //        .stream().map(chambreMapper::toDto).toList();
        return chambreRepository.findByNumeroChambre(id); // à adapter si méthode renvoie ChambreDTO
    }

    /*@Override
    public List<Chambre> getChambresParNomUniversite(String nomUniversite) {
        // 1. Récupérer l'Université par son nom
        Universite universite = universiteRepository.findByNomUniversite(nomUniversite);
        if (universite == null) {
            throw new RuntimeException("Université avec le nom " + nomUniversite + " introuvable");
        }

        // 2. Récupérer le Foyer de l'Université
        Foyer foyer = universite.getFoyer();
        if (foyer == null) {
            throw new RuntimeException("Aucun foyer associé à l'université " + nomUniversite);
        }

        // 3. Récupérer les Blocs du Foyer
        List<Bloc> blocs = foyer.getBlocs();
        if (blocs == null || blocs.isEmpty()) {
            throw new RuntimeException("Aucun bloc dans le foyer de l'université " + nomUniversite);
        }

        // 4. Récupérer toutes les Chambres de tous les Blocs
        List<Chambre> chambres = new ArrayList<>();
        for (Bloc bloc : blocs) {
            if (bloc.getChambres() != null) {
                chambres.addAll(bloc.getChambres());
            }
        }

        if (chambres.isEmpty()) {
            throw new RuntimeException("Aucune chambre trouvée dans l'université " + nomUniversite);
        }

        return chambres;
    }
*/
    @Override
    public List<Chambre> getChambresParNomUniversite(String nomUniversite) {
        return chambreRepository.findChambresParNomUniversite(nomUniversite);
    }
    @Override
    public List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre typeC) {
        // Solution 1 : Utiliser la méthode Keywords
        // return chambreRepository.findByBlocIdBlocAndTypeC(idBloc, typeC);

        // Solution 2 : Utiliser la méthode JPQL
        return chambreRepository.findChambresParBlocEtType(idBloc, typeC);
    }

    @Override
    public List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(String nomUniversite, TypeChambre type) {
        // Vérifier les paramètres
        if (nomUniversite == null || nomUniversite.trim().isEmpty()) {
            throw new RuntimeException("Le nom de l'université ne peut pas être vide");
        }
        if (type == null) {
            throw new RuntimeException("Le type de chambre ne peut pas être null");
        }

        // Appeler la méthode du repository
        List<Chambre> chambres = chambreRepository.findChambresNonReserveParNomUniversiteEtTypeChambre(nomUniversite, type);

        if (chambres == null || chambres.isEmpty()) {
            throw new RuntimeException("Aucune chambre non réservée de type " + type +
                    " trouvée dans l'université " + nomUniversite);
        }

        return chambres;
    }

}
