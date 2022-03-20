package mysecurity.myjwt;

import mysecurity.myjwt.core.security.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtConfig.class)
public class MyjwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyjwtApplication.class, args);
	}

}
