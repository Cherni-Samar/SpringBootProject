package tn.esprit.firstdemo.service.bloc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.firstdemo.repository.bloc.IBlocRepository;

@Service
public class BlocServiceImpl implements IBlocService {
    @Autowired
    IBlocRepository blocRepository;
}
