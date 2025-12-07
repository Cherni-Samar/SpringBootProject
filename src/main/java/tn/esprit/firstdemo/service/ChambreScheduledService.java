package tn.esprit.firstdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit. firstdemo.entity.Bloc;
import tn.esprit.firstdemo.entity.Chambre;
import tn.esprit.firstdemo.entity.TypeChambre;
import tn.esprit.firstdemo.repository.IBlocRepository;
import tn.esprit.firstdemo.repository.IChambreRepository;

import java.time.Year;
import java.util.List;

@Service
public class ChambreScheduledService {

    @Autowired
    private IChambreRepository chambreRepository;

    @Autowired
    private IBlocRepository blocRepository;
    // Service 01: Se déclenche toutes les minutes
    @Scheduled(fixedRate = 60000) // 60000 ms = 1 minute
    @Transactional(readOnly = true)  // Important pour éviter LazyInitializationException
    public void listeChambresParBloc() {
        List<Bloc> blocs = blocRepository.findAll();

        for (Bloc bloc : blocs) {
            List<Chambre> chambres = bloc.getChambres();

            // Afficher le bloc avec sa capacité
            System.out.println("INFO 9884 --- [scheduling-1] t.e.s.Services. Chambre.ChambreService    : Bloc => " +
                    bloc.getNomBloc() + " ayant une capacité " + bloc.getCapaciteBloc());

            // Vérifier s'il y a des chambres dans ce bloc
            if (chambres != null && ! chambres.isEmpty()) {
                System.out.println("INFO 9884 --- [scheduling-1] t.e.s.Services.Chambre.ChambreService    : La liste des chambres pour ce bloc:");

                for (Chambre chambre : chambres) {
                    System.out.println("INFO 9884 --- [scheduling-1] t.e.s.Services.Chambre.ChambreService    : NumChambre: " +
                            chambre.getNumeroChambre() + " type: " + chambre.getTypeC());
                    System.out.println("INFO 9884 --- [scheduling-1] t.e.s.Services. Chambre.ChambreService    : *******************");
                }
            } else {
                System.out. println("INFO 9884 --- [scheduling-1] t.e.s.Services.Chambre.ChambreService    : Pas de chambre disponible dans ce bloc");
                System.out.println("INFO 9884 --- [scheduling-1] t.e.s.Services. Chambre.ChambreService    : *******************");
            }
        }
    }

    // Service 02: Se déclenche toutes les 5 minutes
    @Scheduled(fixedRate = 300000) // 300000 ms = 5 minutes
    @Transactional(readOnly = true)
    public void pourcentageChambreParTypeChambre() {
        List<Chambre> chambres = chambreRepository.findAll();

        long totalChambres = chambres.size();

        if (totalChambres == 0) {
            System.out. println("INFO 19104 --- [scheduling-1] t.e.s.Services. Chambre.ChambreService    : Aucune chambre trouvée");
            return;
        }

        long simpleCount = chambres.stream(). filter(c -> c.getTypeC() == TypeChambre.SIMPLE). count();
        long doubleCount = chambres.stream().filter(c -> c.getTypeC() == TypeChambre.DOUBLE).count();
        long tripleCount = chambres.stream().filter(c -> c.getTypeC() == TypeChambre. TRIPLE).count();

        double simplePercentage = (simpleCount * 100.0) / totalChambres;
        double doublePercentage = (doubleCount * 100.0) / totalChambres;
        double triplePercentage = (tripleCount * 100.0) / totalChambres;

        System. out.println("INFO 19104 --- [scheduling-1] t.e.s.Services. Chambre.ChambreService    : Nombre total des chambres: " + totalChambres);
        System.out. println("INFO 19104 --- [scheduling-1] t.e.s.Services.Chambre.ChambreService    : Le pourcentage des chambres pour le type SIMPLE est égale à " + simplePercentage);
        System.out.println("INFO 19104 --- [scheduling-1] t.e.s.Services.Chambre.ChambreService    : Le pourcentage des chambres pour le type DOUBLE est égale à " + doublePercentage);
        System.out.println("INFO 19104 --- [scheduling-1] t.e.s.Services. Chambre.ChambreService    : Le pourcentage des chambres pour le type TRIPLE est égale à " + triplePercentage);
    }

    // Service 03: Se déclenche toutes les 5 minutes
    @Scheduled(fixedRate = 300000) // 300000 ms = 5 minutes
    @Transactional(readOnly = true)
    public void nbPlacesDisponibleParChambreAnneeEnCours() {
        List<Chambre> chambres = chambreRepository.findAll();
        int anneeEnCours = Year.now().getValue();

        for (Chambre chambre : chambres) {
            // Capacité de la chambre selon son type
            int capaciteChambre = getCapaciteParType(chambre.getTypeC());

            // Nombre de réservations valides pour l'année en cours
            long placesOccupees = 0;
            if (chambre. getReservations() != null && !chambre.getReservations().isEmpty()) {
                placesOccupees = chambre. getReservations().stream()
                        .filter(r -> {
                            if (! r.isEstValide() || r.getAnneeUniversitaire() == null) {
                                return false;
                            }
                            // Convertir Date en année
                            java.util.Calendar cal = java.util.Calendar. getInstance();
                            cal.setTime(r.getAnneeUniversitaire());
                            int anneeReservation = cal.get(java.util.Calendar.YEAR);
                            return anneeReservation == anneeEnCours;
                        })
                        .count();
            }

            long placesDisponibles = capaciteChambre - placesOccupees;

            if (placesDisponibles <= 0) {
                System.out.println("INFO 22532 --- [scheduling-1] t.e. s.Services.Chambre. ChambreService    : La chambre " +
                        chambre.getTypeC() + " " + chambre.getNumeroChambre() + " est complete");
            } else {
                System. out.println("INFO 22532 --- [scheduling-1] t.e.s.Services. Chambre.ChambreService    : Le nombre de place disponible pour la chambre " +
                        chambre.getTypeC() + " " + chambre.getNumeroChambre() + " est " + placesDisponibles);
            }
        }
    }
    // Méthode helper pour obtenir la capacité selon le type de chambre
    private int getCapaciteParType(TypeChambre type) {
        switch (type) {
            case SIMPLE:
                return 1;
            case DOUBLE:
                return 2;
            case TRIPLE:
                return 3;
            default:
                return 0;
        }
    }
}