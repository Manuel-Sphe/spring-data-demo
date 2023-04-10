package sphe.dev.cruddemo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sphe.dev.cruddemo.dao.StudentDAO;
import sphe.dev.cruddemo.entity.Student;

import java.util.List;

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
			readStudent(studentDAO);

			readAllStudents(studentDAO);
			readByName(studentDAO);

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

	private void readStudent(StudentDAO studentDAO) {
		// Create a student

		System.out.println("Creating the student");
		Student studentNew = new Student("Paul","Heyman","pauleh@wwe.com");

		// Saving the student

		System.out.println("Saving the student");
		studentDAO.save(studentNew);

		// Reading the student

		System.out.println("Reading the student with id " + studentNew.getId());

		Student ansStudent  = studentDAO.findById(studentNew.getId());

		System.out.println("Student found: " + ansStudent);


	}
	private void readAllStudents(StudentDAO studentDAO) {
		// Create a student
		List<Student> listOfStudents = studentDAO.findAll();

		System.out.println("\nPrinting the list of students");
		// Printing the list of students
		listOfStudents.forEach(System.out::println);
	}

	private void readByName(StudentDAO studentDAO) {
		// Search for students by name
		List<Student> listOfStudents = studentDAO.findByName("Heyman","Paul");

		System.out.println("Printing the list of students -- Search by first and last name");
		// Printing the list of students
		listOfStudents.forEach(System.out::println);
	}

}
