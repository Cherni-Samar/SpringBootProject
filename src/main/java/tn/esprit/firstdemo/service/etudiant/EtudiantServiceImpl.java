package tn.esprit.firstdemo.service.etudiant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.firstdemo.repository.etudiant.IEtudiantRepository;
import tn.esprit.firstdemo.service.chambre.IChambreService;

@Service
public class EtudiantServiceImpl implements IEtudiantService {
    @Autowired
    IEtudiantRepository etudiantRepository;
}
