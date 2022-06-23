package tr.com.metea.hotelium.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import tr.com.metea.hotelium.domain.Town;

import java.util.List;

/**
 * @author Mete Aydin
 * @since 23.06.2022
 */
public interface TownRepository extends JpaRepository<Town, String>, JpaSpecificationExecutor<Town> {
    List<Town> findTownsByCityId(String cityId);
}
