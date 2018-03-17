package masterSpringMvc;

import masterSpringMvc.chapter2.config.PictureUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties({PictureUploadProperties.class})
public class MasterspringmvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(MasterspringmvcApplication.class, args);
	}
}
