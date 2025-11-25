package tn.esprit.firstdemo.service;

import tn.esprit.firstdemo.dto.ChambreDTO;
import tn.esprit.firstdemo.entity.Bloc;
import tn.esprit.firstdemo.entity.Chambre;

import java.util.List;

public interface IChambreService {

    public List<Chambre> retrieveAllChambres();
    public Chambre retrieveChambre(Long chambreId);
    public Chambre addChambre(Chambre c);
    public void removeChambre(Long chambreId);
    public Chambre modifyChambre(Chambre chambre);
    ChambreDTO assignBlocToChambre(Long idChambre, Long idBloc);

    Bloc addBlocAndChambre(Bloc bloc);
    public List<ChambreDTO> findChambreByNumero(Long id);
}
