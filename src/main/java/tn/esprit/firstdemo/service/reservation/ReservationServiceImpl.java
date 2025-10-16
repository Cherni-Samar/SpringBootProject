package tn.esprit.firstdemo.service.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.firstdemo.repository.reservation.IReservationRepository;

@Service
public class ReservationServiceImpl implements IReservationService {
    @Autowired
    IReservationRepository reservationRepository;
}
