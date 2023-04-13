package sphe.dev.cruddemo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sphe.dev.cruddemo.dao.StudentDAO;
import sphe.dev.cruddemo.entity.Student;

import java.util.List;
import java.util.UUID;

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

			deleteStudent(studentDAO);
			updateStudent(studentDAO);

			createStudent(studentDAO);
			readStudent(studentDAO);

			readAllStudents(studentDAO);
			
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

	private void updateStudent(StudentDAO studentDAO) {
		// Create a student
		Student oldStudent = studentDAO.findById(UUID.fromString("252905cd-8200-4364-b418-d47b50b72ca4"));



		// Update the student
		oldStudent.setFirstName("Brock");
		oldStudent.setLastName("Lesnar");
		oldStudent.setEmail("brocklesnar@wwe.com");

		// Pass the updated student to the DAO
		studentDAO.update(oldStudent);

		// Print the updated student
		System.out.println("Printing the updated student");
		System.out.println(studentDAO.findById(UUID.fromString("252905cd-8200-4364-b418-d47b50b72ca4")));

		Student student1 = studentDAO.findById(UUID.fromString("f176c83e-e967-47ef-b843-e2ef0d377cd0"));

		student1.setFirstName("John");
		student1.setLastName("Cena");
		student1.setEmail("johncena12@wwe.com");

		studentDAO.update(student1);
		System.out.println("Printing the updated student");
		System.out.println(studentDAO.findById(student1.getId()));
	}

	private void deleteStudent(StudentDAO studentDAO) {
		// Create a student
		studentDAO.delete(UUID.fromString("a04869ae-8d1d-4cac-bbf1-85d9439c52e1"));

		List<Student> students = studentDAO.findAll();

		students.forEach((student -> {
			UUID lesnar = UUID.fromString("252905cd-8200-4364-b418-d47b50b72ca4");
			UUID cena = UUID.fromString("f176c83e-e967-47ef-b843-e2ef0d377cd0");
			// Delete all students except Brock Lesnar and John Cena
			if(!student.getId().equals(lesnar) && !student.getId().equals(cena))
				studentDAO.delete(student.getId());
		}));
	}
}
