package hu.czirko.demo.jpa.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFilter;

import javax.persistence.*;

@Entity
@Table(name = "city")
@JsonFilter("cityListFilter")
@NamedQuery(name = "City.findCitiesOfCountry", query = "SELECT c FROM City c WHERE c.country.code = :code")
@NamedQuery(name="City.findByPopulationBigger",query = "SELECT c FROM City c WHERE c.population>= :targetPop")

public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "Name")
    private String name;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "countrycode", referencedColumnName = "code")
    private Country country;

   /* @Column(name = "countrycode")
    private String countryCode;*/

    @Column(name = "District")
    private String district;

    @Column(name = "Population")
    private int population;


   /* public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country=" + country +
                ", district='" + district + '\'' +
                ", population=" + population +
                '}';
    }
}
