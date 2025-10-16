package tn.esprit.firstdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.firstdemo.service.DependancyServiceImpl;
import tn.esprit.firstdemo.service.IDependancyService;

@RestController("dependancy")
//@component
public class DependancyController {

    @Autowired
    IDependancyService dependanceService ;


}
