package tn.esprit.firstdemo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.firstdemo.service.bloc.IBlocService;

@RestController("bloc")
public class BlocController {
    @Autowired
    IBlocService blocService;
}
