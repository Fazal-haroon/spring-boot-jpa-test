package com.example.demo.repository;

import com.example.demo.entity.Course;
import com.example.demo.entity.Guardian;
import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {
    @Autowired
    private CourseRepository repository;

    @Test
    public void printCourses() {
        List<Course> courseList = repository.findAll();
        System.out.println("courseList = " + courseList);
    }

    @Test
    public void saveCourseWithTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("Amir")
                .lastName("Saeed")
                .build();

        Course course = Course.builder()
                .title("Microservices")
                .credit(6)
                .teacher(teacher)
                .build();
        repository.save(course);
    }

    @Test
    public void findAllPagination(){
        Pageable firstPageWithThreeRecords = PageRequest.of(0, 3);
        Pageable secondPageWithTwoRecords = PageRequest.of(1, 2);
        List<Course> courses = repository.findAll(firstPageWithThreeRecords).getContent();
        long totalElements = repository.findAll(firstPageWithThreeRecords).getTotalElements();
        long totalpages = repository.findAll(firstPageWithThreeRecords).getTotalPages();
        System.out.println("totalpages = " + totalpages);
        System.out.println("totalElements = " + totalElements);
        System.out.println("courses = " + courses);
        List<Course> courses1 = repository.findAll(secondPageWithTwoRecords).getContent();
        long totalElements1 = repository.findAll(secondPageWithTwoRecords).getTotalElements();
        long totalpages1 = repository.findAll(secondPageWithTwoRecords).getTotalPages();
        System.out.println("totalpages = " + totalpages1);
        System.out.println("totalElements = " + totalElements1);
        System.out.println("courses = " + courses1);
    }

    @Test
    public void findAllSorting() {
        Pageable sortByTitle = PageRequest.of(0, 2, Sort.by("title"));
        Pageable sortByCreditDesc = PageRequest.of(0, 2, Sort.by("credit").descending());
        Pageable sortByTitleAndCreditDesc = PageRequest.of(0, 2, Sort.by("title").descending().and(Sort.by("credit")));
        List<Course> courses = repository.findAll(sortByTitle).getContent();
        System.out.println("courses = " + courses);
    }

    @Test
    public void printFindByTitleContaining() {
        Pageable firstPageTenRecords = PageRequest.of(0, 10);
        List<Course> courses = repository.findByTitleContaining("D", firstPageTenRecords).getContent();
        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("Danish")
                .lastName("Sir")
                .build();

        Guardian guardian = Guardian.builder()
                .name("Bahadur")
                .email("bahadur@gmail.com")
                .mobile("92342432")
                .build();

        Guardian guardian1 = Guardian.builder()
                .name("Bahadur2")
                .email("bahadur22@gmail.com")
                .mobile("92342432226")
                .build();

        Student student = Student.builder()
                .firstName("Ismail")
                .lastName("Khan")
                .emailId("ismail.khan@gmail.com")
//                .guardian(guardian)
                .build();

        Student student1 = Student.builder()
                .firstName("Haseeb")
                .lastName("Ameer")
                .emailId("khan.haseeb@gmail.com")
                .guardian(guardian1)
                .build();

        Course course = Course.builder()
                .title("DSA")
                .credit(9)
                .teacher(teacher)
                .build();

//        course.addStudents(student);
        course.addStudents(student1);
        repository.save(course);
    }
}