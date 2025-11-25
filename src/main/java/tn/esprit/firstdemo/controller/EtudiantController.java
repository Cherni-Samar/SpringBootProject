package tn.esprit.firstdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.firstdemo.entity.Etudiant;
import tn.esprit.firstdemo.service.IEtudiantService;

import java.util.List;

@RestController
@RequestMapping("/etudiant")
public class EtudiantController {

    @Autowired
    private IEtudiantService etudiantService;

    @GetMapping("/all")
    public List<Etudiant> getAllEtudiants() {
        return etudiantService.getAllEtudiants();
    }

    @GetMapping("/{id}")
    public Etudiant getEtudiantById(@PathVariable("id") Long id) {
        return etudiantService.getEtudiantById(id);
    }

    @PostMapping("/add")
    public Etudiant addEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantService.addEtudiant(etudiant);
    }

    @PutMapping("/update")
    public Etudiant modifyEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantService.modifyEtudiant(etudiant);
    }

    @DeleteMapping("/delete/{id}")
    public void removeEtudiant(@PathVariable("id") Long id) {
        etudiantService.removeEtudiant(id);
    }
}
