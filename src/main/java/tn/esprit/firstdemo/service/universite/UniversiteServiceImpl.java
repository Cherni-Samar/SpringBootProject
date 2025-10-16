package tn.esprit.firstdemo.service.universite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.firstdemo.repository.universite.IUniversiteRepository;

@Service
public class UniversiteServiceImpl implements IUniversiteService {
    @Autowired
    IUniversiteRepository universiteRepository;
}
