package tn.esprit.firstdemo.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Primary
//@Qualifier("x")
public class DependancyRepositoryImpl implements IDependancyRepository {

    @Override
    public String helloWorld() {
        return "dependanceRepositoryImpl";
    }
}
