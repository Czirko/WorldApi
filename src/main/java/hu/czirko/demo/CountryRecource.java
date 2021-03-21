package hu.czirko.demo;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import hu.czirko.demo.jpa.domain.City;
import hu.czirko.demo.jpa.domain.Country;
import hu.czirko.demo.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/country")
public class CountryRecource {
    private final String CITY_LIST_FILTER_KEY = "cities";

    @Autowired
    private DataService data;



    @GetMapping("/all")
    public MappingJacksonValue getAllCountry() {
    List<EntityModel<Country>> resource = StreamSupport
                .stream(data.getAllCountry().spliterator(), false)
                .map(c -> EntityModel.of(c,
                        linkTo(methodOn(CountryRecource.class)
                                .getCitiesOfCountry(c.getCode()))
                                .withRel("bububu")))
                .collect(Collectors.toList());

        return getExceptMappingValues(resource,new HashSet<String>(Arrays.asList(CITY_LIST_FILTER_KEY)));
    }

    @GetMapping("/citiesofcountry/{code}")
    public MappingJacksonValue getCitiesOfCountry(@PathVariable String code) {
        //TODO szar ahogy van ez a funkció, sztrem kéne egy @namedQueryt csinálni
        return getOnlyMappingValues(data.findCountryByCode(code)
                ,new HashSet<String>(Arrays.asList(CITY_LIST_FILTER_KEY)));
    }

    @GetMapping("/{code}")
    public MappingJacksonValue getCountryById(@PathVariable String code) {
        return getExceptMappingValues(data.findCountryByCode(code),
                new HashSet<String>(Arrays.asList(CITY_LIST_FILTER_KEY)));
    }

    public MappingJacksonValue getExceptMappingValues(Object o, Set<String> filteringFieldname) {
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept(filteringFieldname);
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("cityListFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(o);
        mapping.setFilters(filterProvider);
        return mapping;
    }

    public MappingJacksonValue getOnlyMappingValues(Object o, Set<String> filteringFieldname) {
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(filteringFieldname);
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("cityListFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(o);
        mapping.setFilters(filterProvider);
        return mapping;
    }

}
