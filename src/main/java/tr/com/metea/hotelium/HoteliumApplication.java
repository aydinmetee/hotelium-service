package tr.com.metea.hotelium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class HoteliumApplication {

	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("Turkey"));
	}


	public static void main(String[] args) {
		SpringApplication.run(HoteliumApplication.class, args);
	}

}
