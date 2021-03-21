package hu.czirko.demo;

import hu.czirko.demo.jpa.domain.City;
import hu.czirko.demo.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/city")
public class CityRecources {
    @Autowired
    private DataService data;

    @GetMapping("/all")
    public List<City> getAll() {
        return data.getAllCity();
    }

    @PostMapping("/addcity")
    public ResponseEntity<City> addCity(@Valid @RequestBody City c) {
        City saved = data.saveCity(c);
        URI loc = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(loc).build();
    }

    @GetMapping("/{id}")
    public EntityModel<City> getById(@PathVariable int id) {
        City city= data.findCityById(id);

        EntityModel<City> resource = EntityModel.of(city);

        WebMvcLinkBuilder linkTo =
                linkTo(methodOn(CountryRecource.class).getCountryById(city.getCountry().getCode()));

        resource.add(linkTo.withRel("Country of city"));
        return resource;
    }

    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable int id) {
        data.deleteCityById(id);
    }



}
