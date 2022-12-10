package tr.com.metea.hotelium.service.scheduled;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Service;
import tr.com.metea.hotelium.repository.ScheduledTaskRepository;

/**
 * @author Mete Aydin
 * <p>
 * 19.08.2022
 */
@RequiredArgsConstructor
@Service
public class ScheduledTaskService {

    final static String defaultCronExp = "0 0 0 1/1 * ? *";
    private final ScheduledTaskRepository scheduledTaskRepository;
    private final ScheduledTestService scheduledTestService;

    public void configure(ScheduledTaskRegistrar scheduledTaskRegistrar){
        final var tasks = scheduledTaskRepository.findAllValidScheduledTasks();
        tasks.forEach(scheduledTask -> {
            if(scheduledTask.getCode().equals("TEST1")){
                scheduledTaskRegistrar.addCronTask(scheduledTestService, scheduledTask.getCronExp());
            }
        });
    }
}
