package sphe.dev.cruddemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	// We can use this to run some code when the application starts
	// after the beans have been created
	@Bean
	public CommandLineRunner run(String ...args) throws Exception {
		return runner-> {
			System.out.println("Hello World");
		};
	}
}
