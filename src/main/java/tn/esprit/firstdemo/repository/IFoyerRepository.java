package tn.esprit.firstdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.firstdemo.entity.Foyer;
import tn.esprit.firstdemo.entity.Universite;

public interface IFoyerRepository extends JpaRepository<Foyer,Long> {

}
