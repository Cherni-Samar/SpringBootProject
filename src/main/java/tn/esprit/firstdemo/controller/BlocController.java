package tn.esprit.firstdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.firstdemo.entity.Bloc;
import tn.esprit.firstdemo.service.IBlocService;

import java.util.List;

@RestController
@RequestMapping("/bloc")
public class BlocController {

    @Autowired
    private IBlocService blocService;

    @GetMapping("/all")
    public List<Bloc> getAllBlocs() {
        return blocService.getAllBlocs();
    }

    @GetMapping("/{id}")
    public Bloc getBlocById(@PathVariable("id") Long id) {
        return blocService.getBlocById(id);
    }

    @PostMapping("/add")
    public Bloc addBloc(@RequestBody Bloc bloc) {
        return blocService.addBloc(bloc);
    }

    @PutMapping("/update")
    public Bloc modifyBloc(@RequestBody Bloc bloc) {
        return blocService.modifyBloc(bloc);
    }

    @DeleteMapping("/delete/{id}")
    public void removeBloc(@PathVariable("id") Long id) {
        blocService.removeBloc(id);
    }
    @GetMapping("/capacite")
    public List<Bloc> listBlocCapacite(){
        return blocService.ListBlocCapacite();
    }

    @GetMapping("/chambre/{idChambre}")
    public Bloc findBlocByChambreId(@PathVariable Long idChambre){
        return blocService.findBlocByChambreId(idChambre);
    }

    @GetMapping("filtered")
    public List<Bloc> getFilteredBlocs() {
        return blocService.getBlocsByCapaciteCondition();
    }

    @PutMapping("/affecterChambresABloc/{idBloc}")
    public Bloc affecterChambresABloc(
            @RequestBody List<Long> numChambre,
            @PathVariable("idBloc") long idBloc) {
        return blocService.affecterChambresABloc(numChambre, idBloc);
    }
}
