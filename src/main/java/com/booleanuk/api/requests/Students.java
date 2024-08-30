package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    @ResponseStatus(HttpStatus.CREATED)
    public List<Student> getAll() {
        return this.students;
    }

    @GetMapping("/{firstName}")
    @ResponseStatus(HttpStatus.OK)
    public Student getStudent(@RequestBody Student student, @PathVariable String firstName){

        for (Student s: students){
            if (Objects.equals(s.getFirstName(), firstName)){
                return s;
            }

        }

        return null;

    }

    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student updateStudent(@RequestBody Student student, @PathVariable String firstName){

        for (Student s: students){
            if (Objects.equals(s.getFirstName(), firstName)){
                s.setFirstName(student.getFirstName());
                return s;
            }

        }

        return null;

    }

    @DeleteMapping("/{firstName}")
    @ResponseStatus(HttpStatus.OK)
    public Student deleteStudent(@RequestBody Student student, @PathVariable String firstName){

        for (Student s: students){
            if (Objects.equals(s.getFirstName(), firstName)){
                students.remove(s);
                return s;
            }
        }


        return null;

    }
}
