package tn.esprit.firstdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.firstdemo.service.universite.IUniversiteService;

@RestController("universite")
public class UniversiteController {
    @Autowired
    IUniversiteService universiteService;
}
