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
        if (!this.students.contains(student)) {
            this.students.add(student);
            return student;
        }
        return null;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getAll() {
        return this.students;
    }

    @GetMapping("/{firstName}")
    @ResponseStatus(HttpStatus.OK)
    public Student getOneStudent(@PathVariable String firstName) {
        return this.students.stream().filter(student -> student.getFirstName().equals(firstName)).findFirst().orElse(null);
    }

    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student update(@PathVariable String firstName, @RequestBody Student student) {
        Student studentToUpdate = this.students.stream().filter(s -> s.getFirstName().equals(firstName)).findFirst().orElse(null);
        if (studentToUpdate != null) {
            studentToUpdate.setFirstName(student.getFirstName());
            studentToUpdate.setLastName(student.getLastName());
        }
        return studentToUpdate;
    }

    @DeleteMapping("/{firstName}")
    @ResponseStatus(HttpStatus.OK)
    public Student delete(@PathVariable String firstName) {
        Student student = this.students.stream().filter(s -> s.getFirstName().equals(firstName)).findFirst().orElse(null);
        if (student != null) {
            this.students.remove(student);
        }
        return student;
    }
}
