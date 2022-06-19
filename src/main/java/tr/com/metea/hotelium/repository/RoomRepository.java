package tr.com.metea.hotelium.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import tr.com.metea.hotelium.domain.Room;

import java.util.Optional;

/**
 * @author Mete Aydin
 * @date 23.10.2021
 */
public interface RoomRepository extends JpaRepository<Room, String>, JpaSpecificationExecutor<Room> {
    Optional<Room> findByIdAndOrgId(String id, String orgId);
}
