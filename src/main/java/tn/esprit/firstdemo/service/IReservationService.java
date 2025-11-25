package tn.esprit.firstdemo.service;

import tn.esprit.firstdemo.entity.Reservation;

import java.util.Date;
import java.util.List;

public interface IReservationService {
    List<Reservation> getAllReservations();
    Reservation getReservationById(Long idReservation);
    Reservation addReservation(Reservation reservation);
    Reservation modifyReservation(Reservation reservation);
    void removeReservation(Long idReservation);
    // Nouvelle méthode
    Reservation ajouterReservation(long idBloc, long cinEtudiant);

    Reservation annulerReservation(long cinEtudiant);

    // Nouvelle méthode
    List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(Date anneeUniversite, String nomUniversite);

}
