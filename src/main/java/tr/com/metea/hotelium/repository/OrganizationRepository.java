package tr.com.metea.hotelium.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.metea.hotelium.domain.Organization;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, String> {
    List<Organization> findAllByCode(String code);
}
