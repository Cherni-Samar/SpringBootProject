package tn.esprit.firstdemo.repository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
//kol classe intercace repo et controller
@Repository
public class DependancyRepositoryImpl2 implements IDependancyRepository {
    @Override
    public String helloWorld() {
        return "dependancyRepositoryImpl2";
    }

}
