package tn.esprit.firstdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.firstdemo.dto.ChambreDTO;
import tn.esprit.firstdemo.entity.Chambre;

import java.util.List;

public interface IChambreRepository extends JpaRepository<Chambre,Long> {
    public Chambre findByNumeroChambre(long x );

}
