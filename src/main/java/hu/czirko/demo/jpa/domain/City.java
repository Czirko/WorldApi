package hu.czirko.demo.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "city")
public class City  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Name")
    private String name;

    //@Column(name = "CountryCode")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CountryCode",nullable = true)
    private Country country;

    @Column(name = "District")
    private String district;

    @Column(name = "Population")
    private Integer population;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountryCode(Country country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }
}

