package tn.esprit.firstdemo.service;

import tn.esprit.firstdemo.entity.Foyer;
import java.util.List;

public interface IFoyerService {
    List<Foyer> getAllFoyers();
    Foyer getFoyerById(Long idFoyer);
    Foyer addFoyer(Foyer foyer);
    Foyer modifyFoyer(Foyer foyer);
    void removeFoyer(Long idFoyer);
}
