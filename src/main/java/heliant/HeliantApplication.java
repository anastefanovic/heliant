package heliant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HeliantApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeliantApplication.class, args);
	}

}
