package rs.gov.mduls.einicijative.nipub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NipubApplication  {

	public static void main(String[] args) {
		SpringApplication.run(NipubApplication.class, args);
	}

}
