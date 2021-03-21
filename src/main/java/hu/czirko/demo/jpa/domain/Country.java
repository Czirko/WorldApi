package hu.czirko.demo.jpa.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.awt.*;
import java.util.Set;

@Entity
@Table(name = "country")
public class Country {
    @Id

    @Column(name = "Code")
    @Size(min = 3,max = 3)
    private String code;

    @Column(name = "Name")
    private String name;

    @Column(name = "Continent")
    private String continent;

    @Column(name = "Region")
    private String region;

    @Column(name = "Surfacearea")
    private Float surfaceArea;

    @Column(name = "Indepyear")
    private Integer indepYear;

    @Column(name = "Population")
    private Integer population;

    @Column(name = "Lifeexpectancy")
    private Float lifeExpectancy;

    @Column(name = "GNP")
    private Float gnp;

    @Column(name = "Gnpold")
    private Float gnpold;

    @Column(name = "Localname")
    private String localName;

    @Column(name = "Governmentform")
    private String governmentForm;

    @Column(name = "Headofstate")
    private String headOfState;

    @Column(name = "capital")
    private Integer capital;

    @Column(name = "code2")
    private String code2;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="Countrycode")
    private Set<Countrylanguage> languages;

    @Transient
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="Countrycode")
    private Set<City> cities;


    public Set<Countrylanguage> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Countrylanguage> languages) {
        this.languages = languages;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Float getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(Float surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public Integer getIndepYear() {
        return indepYear;
    }

    public void setIndepYear(Integer indepYear) {
        this.indepYear = indepYear;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Float getLifeExpectancy() {
        return lifeExpectancy;
    }

    public void setLifeExpectancy(Float lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }

    public Float getGnp() {
        return gnp;
    }

    public void setGnp(Float gnp) {
        this.gnp = gnp;
    }

    public Float getGnpold() {
        return gnpold;
    }

    public void setGnpold(Float gnpold) {
        this.gnpold = gnpold;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getGovernmentForm() {
        return governmentForm;
    }

    public void setGovernmentForm(String governmentForm) {
        this.governmentForm = governmentForm;
    }

    public String getHeadOfState() {
        return headOfState;
    }

    public void setHeadOfState(String headOfState) {
        this.headOfState = headOfState;
    }

    public Integer getCapital() {
        return capital;
    }

    public void setCapital(Integer capital) {
        this.capital = capital;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }


    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                '}';
    }
}
