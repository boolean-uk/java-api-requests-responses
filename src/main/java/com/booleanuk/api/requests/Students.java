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

    @GetMapping("/{name}")
    public Student getOneStudent(@PathVariable String name) {
        Student toReturn;
        for (Student student : this.students) {
            if (student.getFirstName().equals(name)) {
                toReturn = student;
                return toReturn;
            }
        } return null;

    }

    @PutMapping("/{name}")
    public Student putStudent(@PathVariable String name, @RequestBody Student student) {
        Student toPut = null;
        for (Student person : this.students) {
            if (person.getFirstName().equals(name)) {
                person.setFirstName(student.getFirstName());
                person.setLastName(student.getLastName());
                toPut = person;
            }
        } return toPut;
    }


    @DeleteMapping("/{name}")
    public Student delete(@PathVariable String name) {
        Student toDelete;
        for (Student student : this.students) {
            if (student.getFirstName().equals(name)) {
                toDelete = student;
                students.remove(student);
                return toDelete;
            }
        } return null;

    }


}
