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
    @GetMapping("{id}")
    public Student getOneStudent(@PathVariable int id) {
        if(id < this.students.size()) {
            return this.students.get(id);
        }
        return null;
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student update(@PathVariable int id, @RequestBody Student student) {
        if(id < this.students.size()) {
            this.students.get(id).setFirstName(student.getFirstName());
            this.students.get(id).setLastName(student.getLastName());
            return this.students.get(id);
        }
        return null;
    }

    @DeleteMapping("{id}")
    public Student delete(@PathVariable int id) {
        if(id < this.students.size()) {
            return this.students.remove(id);
        }
        return null;
    }
}
