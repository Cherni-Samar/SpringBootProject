package tn.esprit.firstdemo.service.foyer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.firstdemo.repository.foyer.IFoyerRepository;

@Service
public class FoyerServiceImpl implements IFoyerService {
    @Autowired
    IFoyerRepository foyerRepository;
}
