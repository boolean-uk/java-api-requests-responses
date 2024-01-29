package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class Students {
    private List<Student> students;

    public Students() {
        this.students = new ArrayList<>();
        this.students.add(new Student("Nathan", "King"));
        this.students.add(new Student("Dave", "Ames"));
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

    @GetMapping("/{firstName}")
    public ResponseEntity<Student> getOne(@PathVariable(name = "firstName") String firstName) {
        for (Student student : this.students) {
            if (student.getFirstName().equals(firstName)) {
                return ResponseEntity.ok(student);
            }
        }
        return null;
    }

    @PutMapping("/{firstName}")
    public ResponseEntity<Student> update(@PathVariable(name = "firstName") String firstName, @RequestBody Student reqStudent) {
        for (Student student : this.students) {
            if (student.getFirstName().equals(firstName)) {
                student.setFirstName(reqStudent.getFirstName());
                student.setLastName(reqStudent.getLastName());
                return new ResponseEntity<>(student, HttpStatus.CREATED);
            }
        }
        return null;
    }

}
