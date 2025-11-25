package tn.esprit.firstdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.firstdemo.entity.Reservation;

import java.util.List;

public interface IReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findByChambreIdChambre(Long idChambre);

}

