package tr.com.metea.hotelium.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import tr.com.metea.hotelium.domain.City;

import java.util.List;

/**
 * @author Mete Aydin
 * @since 23.06.2022
 */
public interface CityRepository extends JpaRepository<City, String>, JpaSpecificationExecutor<City> {
    List<City> findCitiesByCountryId(String countryId);
}
