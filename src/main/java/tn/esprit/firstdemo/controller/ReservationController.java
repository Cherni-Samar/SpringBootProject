package tn.esprit.firstdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.firstdemo.service.reservation.IReservationService;

@RestController("reservation")
public class ReservationController {
    @Autowired
    IReservationService reservationService;
}
