package rs.gov.mduls.einicijative.niapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

@SpringBootApplication
@ComponentScan(
		basePackages = {
				"rs.gov.mduls.einicijative.niapi.api.controllers",
				"rs.gov.mduls.einicijative.niapi.db",
				"rs.gov.mduls.einicijative.niapi.clients.euprava",
				"rs.gov.mduls.einicijative.niapi.pdf"
		},
		nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class
)
public class NiapiApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(NiapiApplication.class, args);
	}

}
