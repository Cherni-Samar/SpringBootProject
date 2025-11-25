package tn.esprit.firstdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.firstdemo.entity.Universite;
import tn.esprit.firstdemo.service.IUniversiteService;

import java.util.List;

@RestController
@RequestMapping("/universite")
public class UniversiteController {

    @Autowired
    private IUniversiteService universiteService;

    @GetMapping("/all")
    public List<Universite> getAllUniversites() {
        return universiteService.getAllUniversites();
    }

    @GetMapping("/{id}")
    public Universite getUniversiteById(@PathVariable("id") Long id) {
        return universiteService.getUniversiteById(id);
    }

    @PostMapping("/add")
    public Universite addUniversite(@RequestBody Universite universite) {
        return universiteService.addUniversite(universite);
    }

    @PutMapping("/update")
    public Universite modifyUniversite(@RequestBody Universite universite) {
        return universiteService.modifyUniversite(universite);
    }

    @DeleteMapping("/delete/{id}")
    public void removeUniversite(@PathVariable("id") Long id) {
        universiteService.removeUniversite(id);
    }
    // ← AJOUTER CETTE MÉTHODE
    @PutMapping("/affecterFoyerAUniversite/{idFoyer}/{nomUniversite}")
    public Universite affecterFoyerAUniversite(
            @PathVariable("idFoyer") long idFoyer,
            @PathVariable("nomUniversite") String nomUniversite) {
        return universiteService.affecterFoyerAUniversite(idFoyer, nomUniversite);
    }

    // Nouvelle méthode pour désaffecter
    @PutMapping("/desaffecterFoyerAUniversite/{idUniversite}")
    public Universite desaffecterFoyerAUniversite(@PathVariable("idUniversite") long idUniversite) {
        return universiteService.desaffecterFoyerAUniversite(idUniversite);
    }
}
