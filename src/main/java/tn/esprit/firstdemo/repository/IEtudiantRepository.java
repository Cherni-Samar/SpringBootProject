package tn.esprit.firstdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.firstdemo.entity.Etudiant;

import java.util.List;

public interface IEtudiantRepository extends JpaRepository<Etudiant,Long> {

    List<Etudiant> findAllByReservationsIdReservation(long idReservation);
    //ou bien     List<Etudiant> findByReservations_IdReservation(Long idReservation);

    Etudiant findByCin(long cin);

}
