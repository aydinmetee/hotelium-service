package tr.com.metea.hotelium.repository;

import org.springframework.data.jpa.repository.Query;
import tr.com.metea.hotelium.domain.ScheduledTask;

import java.util.List;

/**
 * @author Mete Aydin
 * <p>
 * 19.08.2022
 */
public interface ScheduledTaskRepository extends BaseRepository<ScheduledTask> {
    @Query("SELECT s FROM ScheduledTask s WHERE s.valid = true")
    List<ScheduledTask> findAllValidScheduledTasks();
}
