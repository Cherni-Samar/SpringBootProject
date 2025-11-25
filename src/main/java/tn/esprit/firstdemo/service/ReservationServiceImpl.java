package tn.esprit.firstdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.firstdemo.entity.*;
import tn.esprit.firstdemo.repository.IBlocRepository;
import tn.esprit.firstdemo.repository.IEtudiantRepository;
import tn.esprit.firstdemo.repository.IReservationRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReservationServiceImpl implements IReservationService {

    @Autowired
    IReservationRepository reservationRepository;

    @Autowired
    IBlocRepository blocRepository;

    @Autowired
    IEtudiantRepository etudiantRepository;



    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(Long idReservation) {
        return reservationRepository.findById(idReservation).orElse(null);
    }

    @Override
    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation modifyReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void removeReservation(Long idReservation) {
        reservationRepository.deleteById(idReservation);
    }

    public Reservation ajouterReservation(long idBloc, long cinEtudiant) {
        // 1. Récupérer le Bloc
        Bloc bloc = blocRepository.findById(idBloc).orElse(null);
        if (bloc == null) {
            throw new RuntimeException("Bloc introuvable");
        }

        // 2. Récupérer l'Étudiant par CIN
        Etudiant etudiant = etudiantRepository.findByCin(cinEtudiant);
        if (etudiant == null) {
            throw new RuntimeException("Étudiant introuvable");
        }

        // 3. Récupérer les chambres du bloc
        List<Chambre> chambres = bloc.getChambres();
        if (chambres == null || chambres.isEmpty()) {
            throw new RuntimeException("Aucune chambre dans ce bloc");
        }

        // 4. Chercher une chambre disponible
        Chambre chambreDisponible = null;
        for (Chambre chambre : chambres) {
            // Compter le nombre de réservations valides pour cette chambre
            int nombreReservations = 0;
            if (chambre.getReservations() != null) {
                for (Reservation r : chambre.getReservations()) {
                    if (r.isEstValide()) {
                        nombreReservations++;
                    }
                }
            }

            // Vérifier la capacité selon le type
            int capaciteMax = 0;
            if (chambre.getTypeC() == TypeChambre.SIMPLE) {
                capaciteMax = 1;
            } else if (chambre.getTypeC() == TypeChambre.DOUBLE) {
                capaciteMax = 2;
            } else if (chambre.getTypeC() == TypeChambre.TRIPLE) {
                capaciteMax = 3;
            }

            // Si la chambre n'est pas pleine
            if (nombreReservations < capaciteMax) {
                chambreDisponible = chambre;
                break;
            }
        }

        if (chambreDisponible == null) {
            throw new RuntimeException("Aucune chambre disponible dans ce bloc");
        }

        // 5. Obtenir l'année actuelle
        int annee = LocalDate.now().getYear();

        // 6. Créer le numéro de réservation : numChambre-nomBloc-anneeUniversitaire
        String numReservation = chambreDisponible.getNumeroChambre() + "-"
                + bloc.getNomBloc() + "-"
                + annee;

        // 7. Créer la réservation
        Reservation reservation = new Reservation();
        reservation.setNumReservation(numReservation);
        reservation.setAnneeUniversitaire(new Date());
        reservation.setEstValide(true);
        reservation.setChambre(chambreDisponible);

        // Ajouter l'étudiant à la réservation
        List<Etudiant> etudiants = new ArrayList<>();
        etudiants.add(etudiant);
        reservation.setEtudiants(etudiants);

        // 8. Sauvegarder la réservation
        Reservation savedReservation = reservationRepository.save(reservation);

        // 9. Ajouter la réservation à la chambre
        if (chambreDisponible.getReservations() == null) {
            chambreDisponible.setReservations(new ArrayList<>());
        }
        chambreDisponible.getReservations().add(savedReservation);

        // 10. Ajouter la réservation à l'étudiant
        if (etudiant.getReservations() == null) {
            etudiant.setReservations(new ArrayList<>());
        }
        etudiant.getReservations().add(savedReservation);

        return savedReservation;
    }
    @Override
    public Reservation annulerReservation(long cinEtudiant) {
        // 1. Récupérer l'Étudiant par CIN
        Etudiant etudiant = etudiantRepository.findByCin(cinEtudiant);
        if (etudiant == null) {
            throw new RuntimeException("Étudiant avec le CIN " + cinEtudiant + " introuvable");
        }

        // 2. Récupérer les réservations de l'étudiant
        List<Reservation> reservations = etudiant.getReservations();
        if (reservations == null || reservations.isEmpty()) {
            throw new RuntimeException("Aucune réservation trouvée pour cet étudiant");
        }

        // 3. Chercher une réservation valide (estValide = true)
        Reservation reservationAnnuler = null;
        for (Reservation reservation : reservations) {
            if (reservation.isEstValide()) {  // ou getEstValide() selon votre Lombok
                reservationAnnuler = reservation;
                break;
            }
        }

        if (reservationAnnuler == null) {
            throw new RuntimeException("Aucune réservation valide à annuler pour cet étudiant");
        }

        // 4. Mettre à jour l'état de la réservation (estValide: false)
        reservationAnnuler.setEstValide(false);

        // 5. Désaffecter l'étudiant de la réservation
        if (reservationAnnuler.getEtudiants() != null) {
            reservationAnnuler.getEtudiants().remove(etudiant);
        }

        // 6. Désaffecter la réservation de l'étudiant
        if (etudiant.getReservations() != null) {
            etudiant.getReservations().remove(reservationAnnuler);
        }

        // 7. Récupérer la chambre associée
        Chambre chambre = reservationAnnuler.getChambre();
        if (chambre != null) {
            // Retirer la réservation de la chambre
            if (chambre.getReservations() != null) {
                chambre.getReservations().remove(reservationAnnuler);
            }

            // Désaffecter la chambre de la réservation
            reservationAnnuler.setChambre(null);
        }

        // 8. Sauvegarder les modifications
        Reservation savedReservation = reservationRepository.save(reservationAnnuler);
        etudiantRepository.save(etudiant);

        return savedReservation;
    }

    @Override
    public List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(Date anneeUniversite, String nomUniversite) {
        // Vérifier que les paramètres ne sont pas null
        if (anneeUniversite == null) {
            throw new RuntimeException("L'année universitaire ne peut pas être nulle");
        }
        if (nomUniversite == null || nomUniversite.trim().isEmpty()) {
            throw new RuntimeException("Le nom de l'université ne peut pas être vide");
        }

        // Appeler la méthode du repository
        List<Reservation> reservations = reservationRepository.findReservationParAnneeUniversitaireEtNomUniversite(
                anneeUniversite, nomUniversite);

        if (reservations == null || reservations.isEmpty()) {
            throw new RuntimeException("Aucune réservation trouvée pour l'année " + anneeUniversite +
                    " et l'université " + nomUniversite);
        }

        return reservations;
    }
}
