package tn.esprit.firstdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.firstdemo.service.foyer.IFoyerService;

@RestController("foyer")
public class FoyerController {
    @Autowired
    IFoyerService foyerService;
}

