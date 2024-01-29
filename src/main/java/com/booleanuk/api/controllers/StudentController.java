package com.booleanuk.api.controllers;

import com.booleanuk.api.models.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {
    private final List<Student> students;

    public StudentController() {
        students = new ArrayList<>();
        students.add(new Student("Nathan", "King"));
        students.add(new Student("Dave", "Ames"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@RequestBody Student student) {
        this.students.add(student);

        return student;
    }

    @GetMapping
    public List<Student> getAll() {
        return this.students;
    }

    // Keep building on already built code
    // Get a student's details using the firstName provided. The first student found with that name.

    @GetMapping("/{firstName}")
    public ResponseEntity<Student> getStudent(@PathVariable String firstName) {
        for (Student student : this.students) {
            if (student.getFirstName().equals(firstName)) {
                return ResponseEntity.ok(student);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{firstName}")
    public ResponseEntity<Student> updateStudent(@PathVariable (name = "firstName")String firstName, @RequestBody Student student) {
        for (Student st : this.students) {
            if (st.getFirstName().equals(firstName)) {
                st.setFirstName(student.getFirstName());
                st.setLastName(student.getLastName());
                return ResponseEntity.ok(st);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{firstName}")
    public ResponseEntity<Student> deleteStudent(@PathVariable (name = "firstName")String firstName) {
        for (int i = 0; i < this.students.size(); i++) {
            if (this.students.get(i).getFirstName().equals(firstName)) {
                return ResponseEntity.ok(this.students.remove(i));
            }
        }
        return ResponseEntity.notFound().build();
    }
}
