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
        add(new Student("Osamah", "Al-maliki"));
    }};

    @GetMapping
    public List<Student> getAll() {
        return this.students;
    }

    @GetMapping("/{firstName}")
    public String getStudentByFirstName(@PathVariable String firstName) {
        for (Student s : students) {
            if (s.getFirstName().equals(firstName)) {
                return s.getFirstName() + " " + s.getLastName();
            }
        }
        return "This student doesn't exist";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@RequestBody Student student) {
        this.students.add(student);
        return student;
    }

    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student updateStudent(@PathVariable String firstName, @RequestBody Student student) {
        for (Student s : this.students) {
            if (s.getFirstName().equals(firstName)) {
                s.setFirstName(student.getFirstName());
                s.setLastName(student.getLastName());
            }
        }
        return null;
    }

    @DeleteMapping("/{firstName}")
    public Student deleteStudent(@PathVariable String firstName) {
        for (Student s : this.students) {
            if (s.getFirstName().equals(firstName)) {
                students.remove(s);
                return s;
            }
        }
        return null;
    }
}
