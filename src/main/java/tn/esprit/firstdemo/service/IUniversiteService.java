package tn.esprit.firstdemo.service;

import tn.esprit.firstdemo.entity.Universite;

import java.util.List;

public interface IUniversiteService {
    List<Universite> getAllUniversites();
    Universite getUniversiteById(Long idUniversite);
    Universite addUniversite(Universite universite);
    Universite modifyUniversite(Universite universite);
    void removeUniversite(Long idUniversite);
    Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite);
    Universite desaffecterFoyerAUniversite(long idUniversite);


}
