package tn.esprit.firstdemo.service.chambre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.firstdemo.repository.chambre.IChambreRepository;
import tn.esprit.firstdemo.service.bloc.IBlocService;

@Service
public class ChambreServiceImpl implements IChambreService {
    @Autowired
    IChambreRepository chambreRepository;
}
