package hu.czirko.demo.service;

import hu.czirko.demo.jpa.domain.City;
import hu.czirko.demo.jpa.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {
    private final CityRepository cityRepo;

    @Autowired
    public DataService(CityRepository cityRepo) {
        this.cityRepo = cityRepo;
    }


    public List<City> getAllCity(){
       return (List<City>) cityRepo.findAll();
    }


}
