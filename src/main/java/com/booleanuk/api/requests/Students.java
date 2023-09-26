package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class Students {
    private List<Student> students;

    public Students() {
        this.students = new ArrayList<>();

        this.students.add(new Student("Nathan", "King"));
        this.students.add(new Student("Dave", "Ames"));
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        this.students.add(student);

        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        return new ResponseEntity<>(this.students, HttpStatus.OK);
    }

    @GetMapping("/{firstname}")
    public ResponseEntity<Student> getStudent(@PathVariable String firstname) {
        for (Student student : students) {
            if (student.getFirstName().equalsIgnoreCase(firstname)) {
                return ResponseEntity.ok(student);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{firstname}")
    public ResponseEntity<Student> update(@PathVariable String firstname, @RequestBody Student updatedStudent) {
        for (Student student : students) {
            if (student.getFirstName().equalsIgnoreCase(updatedStudent.getFirstName())) {
                student.setFirstName(updatedStudent.getFirstName());
                student.setLastName(updatedStudent.getLastName());
                return ResponseEntity.ok(student);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{firstname}")
    public ResponseEntity<Student> delete(@PathVariable String firstname) {
        for(int i = 0; i < students.size(); i++) {
            if (students.get(i).getFirstName().equalsIgnoreCase(firstname)) {
                return ResponseEntity.ok(students.remove(i));
            }
        }
        return ResponseEntity.notFound().build();
    }
}
