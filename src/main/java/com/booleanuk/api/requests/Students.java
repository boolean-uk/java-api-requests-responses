package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class Students {
    private List<Student> students = new ArrayList<>(){{
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
    public ResponseEntity<Student> getStudentByFirstName(@PathVariable String firstName) {
        for (Student s : students) {
            if (s.getFirstName().equals(firstName)) {
                return ResponseEntity.status(HttpStatus.OK).body(s);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{firstName}")
    public ResponseEntity<Student> updateStudentName(@PathVariable String firstName) {
        for (Student s : students) {
            if (s.getFirstName().equals(firstName)) {
                s.setFirstName(s.getFirstName() + "_updated");
                return ResponseEntity.status(HttpStatus.CREATED).body(s);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{firstName}")
    public ResponseEntity<String> deleteStudent(@PathVariable String firstName) {
        for (Student s : students) {
            if (s.getFirstName().equals(firstName)) {
                students.remove(s);
                return ResponseEntity.status(HttpStatus.CREATED).body("Student deleted");
            }
        }
        return ResponseEntity.notFound().build();
    }

}
