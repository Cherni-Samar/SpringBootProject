package tn.esprit.firstdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.firstdemo.service.etudiant.IEtudiantService;

@RestController("etudiant")
public class EtudiantController {
    @Autowired
    IEtudiantService etudiantService;
}
