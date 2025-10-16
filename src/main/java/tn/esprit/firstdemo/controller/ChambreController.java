package tn.esprit.firstdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.firstdemo.service.chambre.IChambreService;

@RestController("chambre")
public class ChambreController {

    @Autowired
    IChambreService chambreService;
}
