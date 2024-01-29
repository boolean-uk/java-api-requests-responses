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
    @ResponseStatus(HttpStatus.OK)
    public Student getOne(@PathVariable(name = "firstName") String firstName) {
        for (Student student : this.students) {
            if (student.getFirstName().equals(firstName)) {
                return student;
            }
        }
        return null;
    }

    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.OK)
    public Student update(@PathVariable(name = "firstName") String firstName, @RequestBody Student reqStudent) {
        for (Student student : this.students) {
            if (student.getFirstName().equals(firstName)) {
                student.setFirstName(reqStudent.getFirstName());
                student.setLastName(reqStudent.getLastName());
                return student;
            }
        }
        return null;
    }

    @DeleteMapping("/{firstName}")
    @ResponseStatus(HttpStatus.OK)
    public Student delete(@PathVariable(name = "firstName") String firstName) {
        for (Student student : this.students) {
            if (student.getFirstName().equals(firstName)) {
                this.students.remove(student);
                return student;
            }
        }
        return null;
    }
}
