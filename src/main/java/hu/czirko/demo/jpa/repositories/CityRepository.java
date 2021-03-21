package hu.czirko.demo.jpa.repositories;

import hu.czirko.demo.jpa.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Integer> {
}
