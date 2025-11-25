package tn.esprit.firstdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.firstdemo.entity.Reservation;

import java.util.Date;
import java.util.List;

public interface IReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findByChambreIdChambre(Long idChambre);

    // Requête JPQL pour récupérer les réservations par année et nom d'université
    @Query("SELECT r FROM Reservation r " +
            "JOIN r.chambre c " +
            "JOIN c.bloc b " +
            "JOIN b.foyer f " +
            "JOIN f.universite u " +
            "WHERE YEAR(r.anneeUniversitaire) = YEAR(:anneeUniversite) " +
            "AND u.nomUniversite = :nomUniversite " +
            "AND r.estValide = true")
    List<Reservation> findReservationParAnneeUniversitaireEtNomUniversite(
            @Param("anneeUniversite") Date anneeUniversite,
            @Param("nomUniversite") String nomUniversite);
}

