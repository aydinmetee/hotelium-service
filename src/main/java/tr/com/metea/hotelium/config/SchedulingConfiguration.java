package tr.com.metea.hotelium.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import tr.com.metea.hotelium.service.scheduled.ScheduledTaskService;

/**
 * @author Mete Aydin
 * <p>
 * 19.08.2022
 */
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class SchedulingConfiguration implements SchedulingConfigurer {

    private final ScheduledTaskService scheduledTaskService;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        scheduledTaskService.configure(taskRegistrar);
    }
}
