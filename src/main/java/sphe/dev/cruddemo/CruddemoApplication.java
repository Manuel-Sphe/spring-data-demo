package sphe.dev.cruddemo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sphe.dev.cruddemo.dao.StudentDAO;
import sphe.dev.cruddemo.entity.Student;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	// We can use this to run some code when the application starts
	// after the beans have been created
	@Bean
	public CommandLineRunner run(@Qualifier("studentDAOImp") StudentDAO studentDAO) throws Exception {
		return runner-> {
			System.out.println("Hello World");
			createStudent(studentDAO);

		};
	}

	private void createStudent(StudentDAO studentDAO) {

		System.out.println("Creating student");
		Student student = new Student("Paul","Doe","pauldoe@gmail.com");

		System.out.println("Saving student");
		studentDAO.save(student);

		System.out.println("Saved student: generated id: ");

		System.out.println(student.getId());

	}
}
