package tn.esprit.firstdemo.service;

import tn.esprit.firstdemo.dto.ChambreDTO;
import tn.esprit.firstdemo.entity.Bloc;
import tn.esprit.firstdemo.entity.Chambre;
import tn.esprit.firstdemo.entity.TypeChambre;

import java.util.List;

public interface IChambreService {

    public List<Chambre> retrieveAllChambres();
    public Chambre retrieveChambre(Long chambreId);
    public Chambre addChambre(Chambre c);
    public void removeChambre(Long chambreId);
    public Chambre modifyChambre(Chambre chambre);
    ChambreDTO assignBlocToChambre(Long idChambre, Long idBloc);

    Bloc addBlocAndChambre(Bloc bloc);
    public Chambre findChambreByNumero(Long id);
    List<Chambre> getChambresParNomUniversite(String nomUniversite);

    // Nouvelle méthode
    List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre typeC);

    // Nouvelle méthode
    List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(String nomUniversite, TypeChambre type);
}
