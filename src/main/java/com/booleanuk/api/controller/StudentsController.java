package com.booleanuk.api.controller;

import com.booleanuk.api.model.Student;
import com.booleanuk.api.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentsController {
    private StudentService studentService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@RequestBody Student student) {
        studentService.getStudents().add(student);

        return student;
    }

    @GetMapping
    public List<Student> getStudents() {

        return studentService.getStudents();
    }

    @GetMapping("/{firstName}")
    public Student getSpecificStudent(@PathVariable String firstName) {

        Student foundStudent = studentService.getSpecificStudent(firstName);
        return foundStudent;
    }
    @PutMapping("/{firstName}")
    public Student updateStudent(@PathVariable String firstName, @RequestBody Student student) {

        Student foundStudent = studentService.updateStudent(firstName, student);
        return foundStudent;
    }
    @DeleteMapping("/{firstName}")
    public Student deleteStudent(@PathVariable String firstName) {
        Student foundStudent = studentService.deleteStudent(firstName);
        return foundStudent;
    }
}
