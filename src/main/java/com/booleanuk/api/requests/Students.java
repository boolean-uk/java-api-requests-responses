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
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getAll() {
        return this.students;
    }

    @GetMapping("/{firstName}")
    @ResponseStatus(HttpStatus.OK)
    public Student getOne(@PathVariable String firstName) {
        for (Student student : this.students) {
            if (student.getFirstName().equals(firstName)) {
                return student;
            }
        }
        return null;
    }

    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student update(@PathVariable String firstName, @RequestBody Student student) {
        for (Student current : this.students) {
            if (current.getFirstName().equals(firstName)) {
                current.setFirstName(student.getFirstName());
                current.setFirstName(student.getFirstName());
                return current;
            }
        }
        return null;
    }

    @DeleteMapping("/{firstName}")
    @ResponseStatus(HttpStatus.OK)
    public Student delete(@PathVariable String firstName) {
        Student student = this.getOne(firstName);
        if (student != null) {
            this.students.remove(student);
        }
        return student;
    }

}
