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
    public Student getOne(@PathVariable(name = "name") String name) {
        for (Student s : students){
            if (s.getFirstName().equals(name)){
                return s;
            }
        }
        return null;
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student update(@PathVariable (name = "name") String name, @RequestBody Student student) {
        for (Student s : students){
            if (s.getFirstName().equals(name)){
                s.setFirstName(student.getFirstName());
                s.setLastName(student.getLastName());
                return s;
            }
        }
        return null;
    }

    @DeleteMapping("/{name}")
    public Student delete(@PathVariable (name = "name") String name) {
        for (Student s : students){
            if (s.getFirstName().equals(name)){
                students.remove(s);
                return s;
            }
        }
        return null;
    }

}
