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

    @GetMapping("/{firstName}")
    public Student getOne(@PathVariable String firstName) {
        for (Student student1 : students) {
            if (student1.getFirstName().equals(firstName)) {
                return student1;
            }

        }
        return null;
    }

    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student updateId(@PathVariable String firstName, @RequestBody Student student) {
        for (Student student1 : students) {
            if (student1.getFirstName().equals(firstName)) {
                student1.setFirstName(student.getFirstName());
                student1.setLastName(student.getLastName());
                return student1;
            }


        }
        return null;
    }


    @DeleteMapping("/{firstName}")
    public Student deleteId(@PathVariable String firstName) {
        for (Student student1 : students) {
            if (student1.getFirstName().equals(firstName)) {

                this.students.remove(student1);
                return student1;
            }

        }
        return null;
    }
}
