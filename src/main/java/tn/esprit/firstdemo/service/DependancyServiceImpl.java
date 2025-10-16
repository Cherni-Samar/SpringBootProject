package tn.esprit.firstdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tn.esprit.firstdemo.repository.IDependancyRepository;

@Service
public class DependancyServiceImpl implements IDependancyService {

    @Autowired
    //@Qualifier("x")
    IDependancyRepository dependancyRepository ;
    // IDependancyRepository x
    //reeleement bch tnedi l repo

}
