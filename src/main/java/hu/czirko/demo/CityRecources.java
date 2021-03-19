package hu.czirko.demo;

import hu.czirko.demo.jpa.domain.City;
import hu.czirko.demo.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityRecources {
    @Autowired
    private DataService data;

    @GetMapping("/all")
    public List<City> getAll() {
        return data.getAllCity();
    }

}
