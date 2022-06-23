package tr.com.metea.hotelium.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import tr.com.metea.hotelium.domain.Country;

import java.util.Optional;

/**
 * @author Mete Aydin
 * @since 23.06.2022
 */
public interface CountryRepository extends JpaRepository<Country, String>, JpaSpecificationExecutor<Country> {
    Optional<Country> findCountryByName(String name);
}
