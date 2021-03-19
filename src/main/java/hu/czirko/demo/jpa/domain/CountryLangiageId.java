package hu.czirko.demo.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CountryLangiageId implements Serializable {

    private String countryCode;


    private String language;

    public CountryLangiageId(String countryCode, String language) {
        this.countryCode = countryCode;
        this.language = language;
    }

    public CountryLangiageId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryLangiageId that = (CountryLangiageId) o;
        return Objects.equals(countryCode, that.countryCode) && Objects.equals(language, that.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, language);
    }
}
