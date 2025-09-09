package dev.ved.kranklink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KranklinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(KranklinkApplication.class, args);
	}

}
