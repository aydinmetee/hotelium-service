package tr.com.metea.hotelium.service;

import tr.com.metea.hotelium.domain.City;
import tr.com.metea.hotelium.domain.Country;
import tr.com.metea.hotelium.domain.Town;

import java.util.List;

/**
 * @author Mete Aydin
 * @since 23.06.2022
 */
public interface LocationService {
    Country getCountryByName(String countryName);

    Country getCountryById(String id);

    City getCityByName(String cityName);

    City getCityById(String id);
    Town getTownByName(String townName);

    Town getTownById(String id);

    List<City> getCitiesByCountry(String countryId);

    List<Town> getTownsByCity(String cityId);

    List<Country> getCountries();
}
