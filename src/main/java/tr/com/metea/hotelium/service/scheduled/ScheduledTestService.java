package tr.com.metea.hotelium.service.scheduled;

import org.springframework.stereotype.Service;

/**
 * @author Mete Aydin
 * <p>
 * 19.08.2022
 */
@Service
public class ScheduledTestService implements Runnable{
    @Override
    public void run() {
        System.out.println("Hello, this message come from Test Service");
    }
}
