package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.OK)
    public Student update(@PathVariable String firstName, @RequestBody Student newName) {
        for (int i = 0; i<this.students.size(); i++) {
            if (this.students.get(i).getFirstName().equals(firstName)) {
                this.students.get(i).setFirstName(newName.getFirstName());
                this.students.get(i).setLastName(newName.getLastName());
                return this.students.get(i);
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
    }

    @DeleteMapping("/{firstName}")
    @ResponseStatus(HttpStatus.OK)
    public Student delete(@PathVariable String firstName) {
        for (int i = 0; i<this.students.size(); i++) {
            if (this.students.get(i).getFirstName().equals(firstName)) {
                return this.students.remove(i);
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
    }

    @GetMapping("/{firstName}")
    @ResponseStatus(HttpStatus.OK)
    public Student getAStudent(@PathVariable String firstName) {
        for (int i = 0; i<this.students.size(); i++) {
            if (this.students.get(i).getFirstName().equals(firstName)) {
                return this.students.get(i);
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");

    }

}
