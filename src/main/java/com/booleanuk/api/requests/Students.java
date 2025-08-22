package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class Students {

    private List<Student> students = new ArrayList<>() {{
        add(new Student("Nathan", "King"));
        add(new Student("Dave", "Ames"));
    }};

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
    public ResponseEntity<?> getStudent(@PathVariable String firstName) {
        for (Student student : students) {
            if (student.getFirstName().equalsIgnoreCase(firstName)) {
                return ResponseEntity.ok(student); // 200 OK
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No student found with the name '" + firstName + "'");
    }

    @PutMapping("/{firstName}")
    public ResponseEntity<?> updateStudentData(@PathVariable String firstName) {
        for (Student student : students) {
            if (student.getFirstName().equalsIgnoreCase(firstName)) {
                return ResponseEntity.status(HttpStatus.CREATED).body(student);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Student to update was not found.");
    }

    @DeleteMapping("/{firstName}")
    public ResponseEntity<?> deleteStudent(@PathVariable String firstName) {
        for (Student student : students) {
            if (student.getFirstName().equalsIgnoreCase(firstName)) {
                students.remove(student);
                return ResponseEntity.ok("Student deleted.");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Student to delete was not found.");
    }
}
