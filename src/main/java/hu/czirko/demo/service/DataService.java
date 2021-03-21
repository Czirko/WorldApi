package hu.czirko.demo.service;

import hu.czirko.demo.error.NotFoundException;
import hu.czirko.demo.jpa.domain.City;
import hu.czirko.demo.jpa.domain.Country;
import hu.czirko.demo.jpa.repositories.CityRepository;
import hu.czirko.demo.jpa.repositories.CountryLanguageRepository;
import hu.czirko.demo.jpa.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataService {
    private final CityRepository cityRepo;
    private final CountryRepository countryRepo;
    private final CountryLanguageRepository countLangRepo;

    @Autowired
    public DataService(CityRepository cityRepo, CountryRepository countryRepo, CountryLanguageRepository countLangRepo) {
        this.cityRepo = cityRepo;
        this.countryRepo = countryRepo;
        this.countLangRepo = countLangRepo;
    }


    public List<City> getAllCity() {
        //cityRepo.findAll().forEach(x-> System.out.println(x));
        return (List<City>) cityRepo.findAll();
    }

    public City saveCity(City c) {
        return cityRepo.save(c);
    }

    public City findCityById(int id) {
        return cityRepo.findById(id).orElseThrow(() -> new NotFoundException("There is no City, with given ID:_" + id));
    }

    public void deleteCityById(int id) {
        cityRepo.delete(cityRepo
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException("There is no City, with given ID:_" + id)));

    }

    public List<Country> getAllCountry() {
        //cityRepo.findAll().forEach(x-> System.out.println(x));
        return (List<Country>) countryRepo.findAll();
    }

    public Country saveCountry(Country c) {
        return countryRepo.save(c);
    }

    public Country findCountryByCode(String code) {
        return countryRepo.findById(code)
                .orElseThrow(() ->
                        new NotFoundException("There is no City, with given Code:_" + code));
    }

    public void deleteCountryByCode(String code) {
        countryRepo.delete(countryRepo
                .findById(code)
                .orElseThrow(() ->
                        new NotFoundException("There is no City, with given Code:_" + code)));

    }

}
