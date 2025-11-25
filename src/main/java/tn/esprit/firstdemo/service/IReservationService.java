package tn.esprit.firstdemo.service;

import tn.esprit.firstdemo.entity.Reservation;
import java.util.List;

public interface IReservationService {
    List<Reservation> getAllReservations();
    Reservation getReservationById(Long idReservation);
    Reservation addReservation(Reservation reservation);
    Reservation modifyReservation(Reservation reservation);
    void removeReservation(Long idReservation);
}
