package com.example.demo.repository;

import com.example.demo.entity.Guardian;
import com.example.demo.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student = Student.builder()
                .emailId("fazal@gmail.com")
                .firstName("fazal")
                .lastName("haroon")
//                .guardianName("Fazal Alim")
//                .guardianEmail("fazal.alim@gmail.com")
//                .guardianMobile("0503467693")
                .build();
        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian(){
        Guardian guardian = Guardian.builder()
                .name("Fazal Alim Daji")
                .mobile("KSA:0503467693")
                .email("ZamaDaji@gmail.com")
                .build();
        Student student = Student.builder()
                .firstName("Fazal")
                .lastName("Haroon")
                .emailId("FazalHaroon@gmail.com")
                .guardian(
                       guardian
                ).build();
        studentRepository.save(student);
    }

    @Test
    public void printStudentByFirstName(){
        List<Student> students = studentRepository.findByFirstName("Fazal");
        System.out.println("students = " + students);
    }

    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> students = studentRepository.findByFirstNameContaining("fa");
        System.out.println("students = " + students);
    }

    @Test
    public void printAllStudent() {
        List<Student> studentList = studentRepository.findAll();
        System.out.println("studentList = " + studentList);
    }

    @Test
    public void printStudentBaseOnGuardianName(){
        List<Student> students = studentRepository.findByGuardianName("Fazal Alim Daji");
        System.out.println("students = " + students);
    }

    @Test
    public void printGetStudentByEmailAddress(){
        Student student = studentRepository.getStudentByEmailAddress("FazalHaroon@gmail.com");
        System.out.println("student = " + student);
    }

    @Test
    public void printGetFirstNameStudentByEmailAddress(){
        String student = studentRepository.getStudentFirstNameByEmailAddress("FazalHaroon@gmail.com");
        System.out.println("student = " + student);
    }

    @Test
    public void printGetStudentByEmailAddressNative(){
        Student student = studentRepository.getStudentByEmailAddressNative("FazalHaroon@gmail.com");
        System.out.println("student = " + student);
    }

    @Test
    public void printGetStudentByEmailAddressNativeNamedParam() {
        Student student = studentRepository.getStudentByEmailAddressNativeNamedParam("FazalHaroon@gmail.com");
        System.out.println("student = " + student);
    }

    @Test
    public void updateStudentNameByEmailIdTest(){
        studentRepository.updateStudentNameByEmailAddress("Javat", "FazalHaroon@gmail.com");
    }
}