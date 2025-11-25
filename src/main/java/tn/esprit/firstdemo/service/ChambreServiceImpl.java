package tn.esprit.firstdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.JdbcAggregateOperations;
import org.springframework.stereotype.Service;
import tn.esprit.firstdemo.dto.ChambreDTO;
import tn.esprit.firstdemo.entity.Bloc;
import tn.esprit.firstdemo.entity.Chambre;
import tn.esprit.firstdemo.mapper.ChambreMapper;
import tn.esprit.firstdemo.repository.IBlocRepository;
import tn.esprit.firstdemo.repository.IChambreRepository;

import java.util.List;

@Service
public class ChambreServiceImpl implements IChambreService {
    @Autowired
    IChambreRepository chambreRepository;

    @Autowired
    IBlocRepository blocRepository;

    @Autowired
    private ChambreMapper chambreMapper;


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


}
