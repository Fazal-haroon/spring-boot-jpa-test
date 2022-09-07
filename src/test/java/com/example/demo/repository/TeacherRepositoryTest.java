package com.example.demo.repository;

import com.example.demo.entity.Course;
import com.example.demo.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository repository;

    @Test
    public void saveTeacher() {
        Course course1 = Course.builder()
                .title("Java")
                .credit(5)
                .build();

        Course course2 = Course.builder()
                .title("Java Advance")
                .credit(8)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("Abdul")
                .lastName("Hafeez")
//                .courseList(Arrays.asList(course1, course2))
                .build();

        Teacher save = repository.save(teacher);
        System.out.println("save = " + save);
    }

}