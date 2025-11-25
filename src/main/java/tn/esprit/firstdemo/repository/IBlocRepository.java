package tn.esprit.firstdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.firstdemo.entity.Bloc;
import tn.esprit.firstdemo.entity.Chambre;

import java.util.List;

public interface IBlocRepository extends JpaRepository<Bloc, Long> {
    @Query("SELECT b FROM Bloc b JOIN b.chambres c WHERE c.idChambre = :idChambre")
    Bloc findBlocByIdChambre(@Param("idChambre") Long idChambre);

    //List<Bloc> findByCapaciteBlocNotBetween(long capaciteBlocAfter, long capaciteBlocBefore);
    List<Bloc> findByCapaciteBlocGreaterThanAndCapaciteBlocLessThan(Long min, Long max);
    //List<Bloc> getBlocsByFoyerId(Long idFoyer);

    List<Bloc> findAllByFoyer_IdFoyer(long foyerIdFoyer);


}
