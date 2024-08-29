package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("students")
public class Students {
    private HashMap<String, Student> students = new HashMap<>() {
        {
            put("Nathan", new Student("Nathan", "King"));
            put("Dave", new Student("Dave", "Ames"));
            put("Harry", new Student("Harry", "Potter"));
        }
    };


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@RequestBody Student student) {
        this.students.put(student.getFirstName(), student);
        return student;
    }

    @GetMapping
    public HashMap<String, Student> getAll() {
        return this.students;
    }

    @GetMapping("{firstname}")
    public Student getSpecific(@PathVariable String firstname) {
        Student student = this.students.get(firstname);
        if (student != null) {
            return student;
        }
        return null;
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student update(
            @PathVariable String id,
            @RequestBody Student student) {

        if (this.students.get(id) != null) {
            this.students.put(id, student);
            return student;
        }
        return null;
    }

    @DeleteMapping("{id}")
    public Student delete(@PathVariable String id) {
        Student student = this.students.get(id);
        if (student!= null) {
            this.students.remove(id);
            return student;
        }
        return null;
    }

}
