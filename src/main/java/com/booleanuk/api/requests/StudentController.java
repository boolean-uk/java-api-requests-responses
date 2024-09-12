package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("students")
public class StudentController {
    private HashMap<String, Student> students;

    public StudentController() {
        students = new HashMap<>();
    }

    @GetMapping
    public HashMap<String, Student> getAllStudents() {
        return this.students;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        this.students.put(student.getFirstName(), student);
        return this.students.get(student.getFirstName());
    }
    
    @GetMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student getSpecificStudent(@PathVariable String firstName) {
        if (students.containsKey(firstName)) {
            return students.get(firstName);
        }
        return null;
    }


    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student updatStudent(@PathVariable String firstName, @RequestBody Student student) {
        if (students.containsKey(firstName)) {
            students.put(firstName, student);
            return this.students.get(firstName);
        }
        return null;
    }


    @DeleteMapping("/{firstName}")
    public HashMap<String, Student> deleteStudent(@PathVariable String firstName) {
        this.students.remove(firstName);
        return this.students;
    }
}
