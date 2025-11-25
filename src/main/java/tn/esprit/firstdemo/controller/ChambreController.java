package tn.esprit.firstdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.firstdemo.dto.ChambreDTO;
import tn.esprit.firstdemo.entity.Bloc;
import tn.esprit.firstdemo.entity.Chambre;
import tn.esprit.firstdemo.service.IChambreService;

import java.util.List;

@RestController
@RequestMapping("/chambre") // base URL: http://localhost:8080/chambre
public class ChambreController {

    @Autowired
    private IChambreService chambreService;

    // 🔹 GET - Récupérer toutes les chambres
    @GetMapping("/all")
    public List<Chambre> getAllChambres() {
        return chambreService.retrieveAllChambres();
    }

    // 🔹 GET - Récupérer une chambre par ID
    @GetMapping("/{id}")
    public Chambre getChambreById(@PathVariable("id") Long idChambre) {
        return chambreService.retrieveChambre(idChambre);
    }

    // 🔹 POST - Ajouter une nouvelle chambre
    @PostMapping("/add")
    public Chambre addChambre(@RequestBody Chambre chambre) {
        return chambreService.addChambre(chambre);
    }

    // 🔹 PUT - Modifier une chambre existante
    @PutMapping("/update")
    public Chambre modifyChambre(@RequestBody Chambre chambre) {
        return chambreService.modifyChambre(chambre);
    }

    // 🔹 DELETE - Supprimer une chambre par ID
    @DeleteMapping("/delete/{id}")
    public void removeChambre(@PathVariable("id") Long idChambre) {
        chambreService.removeChambre(idChambre);
    }

    @PutMapping("/affecter/{idChambre}/{idBloc}")
    public ChambreDTO assignBlocToChambre(@PathVariable Long idChambre, @PathVariable Long idBloc) {
        return chambreService.assignBlocToChambre(idChambre, idBloc);
    }

    @PostMapping("/addBlocandChmabre")
    public Bloc addBlocAndChambres(@RequestBody Bloc bloc){
        return chambreService.addBlocAndChambre(bloc);
    }

}
