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
        for (Student student : this.students) {
            if (student.getFirstName().equals(firstName)) {
                return student;
            }
        }
        return null;
    }

    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student update(@PathVariable String firstName, @RequestBody Student newStudent) {
        for (Student student : this.students) {
            if (student.getFirstName().equals(firstName)) {
                student.setFirstName(newStudent.getFirstName());
                student.setLastName(newStudent.getLastName());
                return student;
            }
        }
        return null;
    }

    @DeleteMapping("/{firstName}")
    public Student delete(@PathVariable String firstName) {
        for (int i = 0; i < this.students.size(); i++) {
            if (this.students.get(i).getFirstName().equals(firstName)) {
                return this.students.remove(i);
            }
        }
        return null;
    }
}
