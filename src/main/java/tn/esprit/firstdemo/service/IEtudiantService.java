package tn.esprit.firstdemo.service;

import tn.esprit.firstdemo.entity.Etudiant;
import java.util.List;

public interface IEtudiantService {
    List<Etudiant> getAllEtudiants();
    Etudiant getEtudiantById(Long idEtudiant);
    Etudiant addEtudiant(Etudiant etudiant);
    Etudiant modifyEtudiant(Etudiant etudiant);
    void removeEtudiant(Long idEtudiant);
}
