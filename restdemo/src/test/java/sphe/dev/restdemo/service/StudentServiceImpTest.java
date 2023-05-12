package sphe.dev.restdemo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sphe.dev.restdemo.entity.Student;
import sphe.dev.restdemo.exception.exceptions.BadRequestException;
import sphe.dev.restdemo.repository.StudentRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@ExtendWith(MockitoExtension.class)
class StudentServiceImpTest {

    @Mock private StudentRepository studentRepository;
    private StudentService underTest ;

    @BeforeEach
    void setUp() {
        underTest = new StudentServiceImp(studentRepository);
    }

    @Test
    void canGetAllStudents() {
        // when
        underTest.findAll();
        verify(studentRepository).findAll();
    }

    @Test
    void canSaveStudent() {
        //given
        Student student = new Student(
                "wendy",
                "shabalala",
                "wendysaselihle@gmail.com"
        );

        underTest.saveStudent(student);
        // when

        // then
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);

        verify(studentRepository).save(studentArgumentCaptor.capture());

        Student capturedStudent = studentArgumentCaptor.getValue();

        assertThat(capturedStudent).isEqualTo(student);

    }

    @Test
    void willThrowExceptionWhenEmailIsTaken() {
        //given
        Student student = new Student(
                "wendy",
                "shabalala",
                "wendysaselihle@gmail.com"
        );

        given(studentRepository.selectExistsEmail(anyString()))
                .willReturn(true);
        // when
        assertThatThrownBy(() -> underTest.saveStudent(student))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Email "+student.getEmail()+" already exists");

        verify(studentRepository,never()).save(any());
    }


    @Test
    @Disabled
    void findById() {
    }

    @Test
    @Disabled
    void updateStudent() {
    }

    @Test
    @Disabled
    void deleteById() {
    }
}