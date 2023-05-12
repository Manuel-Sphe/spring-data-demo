package sphe.dev.restdemo.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sphe.dev.restdemo.entity.Student;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private  StudentRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfStudentEmailExists() {
        // given
        Student student = new Student("Manuel-Sphe", "Madonsela","sphedev@gmail.com");
        underTest.save(student);
        // when

        Boolean expected = this.underTest.selectExistsEmail(student.getEmail());
        // then
        assertThat(expected).isTrue();

    }

    @Test
    void itShouldCheckIfStudentEmailDoesNotExists() {
        // given
        String email = "sphedev@gmail.com";

        // when

        Boolean expected = this.underTest.selectExistsEmail(email);
        // then
        assertThat(expected).isTrue();

    }
}