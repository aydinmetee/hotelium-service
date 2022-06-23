package tr.com.metea.hotelium.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.metea.hotelium.domain.City;
import tr.com.metea.hotelium.domain.Country;
import tr.com.metea.hotelium.domain.Town;
import tr.com.metea.hotelium.exception.ServiceExecutionException;
import tr.com.metea.hotelium.repository.CityRepository;
import tr.com.metea.hotelium.repository.CountryRepository;
import tr.com.metea.hotelium.repository.TownRepository;
import tr.com.metea.hotelium.service.LocationService;

import java.util.List;

/**
 * @author Mete Aydin
 * @since 23.06.2022
 */
@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final TownRepository townRepository;

    @Override
    public Country getCountryByName(String countryName) {
        final var country = countryRepository.findCountryByName(countryName);
        if (country.isEmpty()) {
            throw new ServiceExecutionException("Ülke bulunamadi!");
        }
        return country.get();
    }

    @Override
    public City getCityByName(String cityName) {
        return null;
    }

    @Override
    public Town getTownByName(String townName) {
        return null;
    }

    @Override
    public List<City> getCitiesByCountry(String countryId) {
        return null;
    }

    @Override
    public List<Town> getTownsByCity(String cityId) {
        return null;
    }
}
