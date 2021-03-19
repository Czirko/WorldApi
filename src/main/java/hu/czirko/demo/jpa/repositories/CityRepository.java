package hu.czirko.demo.jpa.repositories;

import hu.czirko.demo.jpa.domain.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City,Integer> {
}
