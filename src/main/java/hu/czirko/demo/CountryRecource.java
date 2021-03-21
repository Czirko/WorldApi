package hu.czirko.demo;

import hu.czirko.demo.jpa.domain.City;
import hu.czirko.demo.jpa.domain.Country;
import hu.czirko.demo.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryRecource {

    @Autowired
    private DataService data;

    @GetMapping("/all")
    public List<Country> getAllCountry() {
        return data.getAllCountry();
    }


    @GetMapping("/{code}")
    public Country getCountryById(@PathVariable String code) {
        return data.findCountryByCode(code);
    }
}
