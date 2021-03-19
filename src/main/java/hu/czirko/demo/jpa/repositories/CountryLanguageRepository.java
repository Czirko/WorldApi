package hu.czirko.demo.jpa.repositories;

import hu.czirko.demo.jpa.domain.Countrylanguage;
import org.springframework.data.repository.CrudRepository;

public interface CountryLanguageRepository extends CrudRepository<Countrylanguage,String> {
}
