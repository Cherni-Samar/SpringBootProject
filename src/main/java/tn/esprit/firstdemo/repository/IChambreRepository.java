package tn.esprit.firstdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.firstdemo.dto.ChambreDTO;
import tn.esprit.firstdemo.entity.Chambre;
import tn.esprit.firstdemo.entity.TypeChambre;

import java.util.List;

public interface IChambreRepository extends JpaRepository<Chambre,Long> {
    public Chambre findByNumeroChambre(long x );

    // Requête JPQL pour récupérer les chambres par nom d'université
    @Query("SELECT c FROM Chambre c " +
            "JOIN c.bloc b " +
            "JOIN b.foyer f " +
            "JOIN f.universite u " +
            "WHERE u.nomUniversite = :nomUniversite")
    List<Chambre> findChambresParNomUniversite(@Param("nomUniversite") String nomUniversite);


    // Solution 1 : Keywords (méthode dérivée)
    List<Chambre> findByBlocIdBlocAndTypeC(long idBloc, TypeChambre typeC);

    // Solution 2 : JPQL
    @Query("SELECT c FROM Chambre c WHERE c.bloc.idBloc = :idBloc AND c.typeC = :typeC")
    List<Chambre> findChambresParBlocEtType(@Param("idBloc") long idBloc, @Param("typeC") TypeChambre typeC);

    // Nouvelle requête : Chambres non réservées par université et type
    @Query("SELECT c FROM Chambre c " +
            "JOIN c.bloc b " +
            "JOIN b.foyer f " +
            "JOIN f.universite u " +
            "WHERE u.nomUniversite = :nomUniversite " +
            "AND c.typeC = :type " +
            "AND c.idChambre NOT IN (" +
            "    SELECT r.chambre.idChambre FROM Reservation r " +
            "    WHERE r.estValide = true " +
            "    AND YEAR(r.anneeUniversitaire) = YEAR(CURRENT_DATE)" +
            ")")
    List<Chambre> findChambresNonReserveParNomUniversiteEtTypeChambre(
            @Param("nomUniversite") String nomUniversite,
            @Param("type") TypeChambre type);
}




