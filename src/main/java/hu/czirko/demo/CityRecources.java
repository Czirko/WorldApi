package hu.czirko.demo;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import hu.czirko.demo.jpa.domain.City;
import hu.czirko.demo.jpa.domain.Country;
import hu.czirko.demo.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/city")
public class CityRecources {
    @Autowired
    private DataService data;

    @GetMapping("/all")
    public MappingJacksonValue getAll() {
        return getJsonMappingWithAll(hateoasForList(data.getAllCity()));
    }

    @PostMapping("/add")
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


    @GetMapping("/popbigger/{target}")
    public MappingJacksonValue getCitiesWithPopulationBiggerThan(@PathVariable int target){
        return getJsonMappingWithAll(hateoasForList(data.citiesPopulationBiggerThan(target)));
    }


    private List<EntityModel<City>> hateoasForList(List<City> lst){
        List<EntityModel<City>> resource = StreamSupport
                .stream(lst.spliterator(), false)
                .map(c -> EntityModel.of(c,
                        linkTo(methodOn(CountryRecource.class)
                                .getCountryById(c.getCountry().getCode()))
                                .withRel("Link to the Country of "+c.getName()+".")))
                .collect(Collectors.toList());
        return resource;
    }

    private MappingJacksonValue getJsonMappingWithAll(List<EntityModel<City>> data){

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAll();
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("cityListFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(data);
        mapping.setFilters(filterProvider);
        return mapping;
    }
}
