package hu.czirko.demo.jpa.repositories;

import hu.czirko.demo.jpa.domain.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<Country,String>{
}
