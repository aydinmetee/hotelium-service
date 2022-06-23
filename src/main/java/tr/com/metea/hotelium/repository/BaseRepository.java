package tr.com.metea.hotelium.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

/**
 * @author Mete Aydin
 * @since 23.06.2022
 */
@NoRepositoryBean
public interface BaseRepository<ENTITY> extends JpaRepository<ENTITY, String>, JpaSpecificationExecutor<ENTITY> {

    Optional<ENTITY> findByIdAndOrgId(String id, String orgId);

}
