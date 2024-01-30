package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class Students {
    private List<Student> students = new ArrayList<>(){{
        add(new Student("Nathan", "King"));
        add(new Student("Dave", "Ames"));
    }};

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> create(@RequestBody Student student) {
        this.students.add(student);
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public List<Student> getAll() {
        return this.students;
    }

    @GetMapping("/{firstName}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Student getStudent(@PathVariable String firstName){
        for (Student student1: students){
            if (student1.getFirstName().equals(firstName)){
                return student1;
            }
        }
        return null;
    }

    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> updateStudent(@PathVariable String firstName, @RequestBody Student student) {
        for (Student s : this.students) {
            if (s.getFirstName().equals(firstName)){
                s.setFirstName(student.getFirstName());
                s.setLastName(student.getLastName());
                return ResponseEntity.ok(s);
            }
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{firstName}")
    public List<Student> deleteStudent(@PathVariable String firstName) {
        Student studentToRemove = null;
        for (Student student : students) {
            if (student.getFirstName().equals(firstName)) {
                studentToRemove = student;
                break;
            }
        }
        if (studentToRemove != null) {
            this.students.remove(studentToRemove);
        }

        return this.students;
    }

}
