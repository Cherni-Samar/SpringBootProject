package tn.esprit.firstdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.firstdemo.entity.Universite;



public interface IUniversiteRepository extends JpaRepository<Universite,Long> {
  Universite findByNomUniversite(String nomUniversite);
}
