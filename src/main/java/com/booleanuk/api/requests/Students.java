package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
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

    @GetMapping("{firstName}")
    public Student getStudent(@PathVariable String firstName) {
        try {
            return students.stream()
                    .filter(student -> student.getFirstName().equals(firstName))
                    .findFirst().orElseThrow();
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping("{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student putStudent(@PathVariable String firstName, @RequestBody Student student) {
        try {
            Student s = students.stream()
                    .filter(temp -> temp.getFirstName().equals(firstName))
                    .findFirst().orElseThrow();
            s.setFirstName(student.getFirstName());
            s.setLastName(student.getLastName());
            return s;
        } catch (Exception e) {
            return null;
        }
    }

    @DeleteMapping("{firstName}")
    public Student removeStudent(@PathVariable String firstName) {
        try {
            Student s = students.stream()
                    .filter(student -> student.getFirstName().equals(firstName))
                    .findFirst().orElseThrow();
            students.remove(s);
            return s;
        } catch (Exception e) {
            return null;
        }
    }
}
