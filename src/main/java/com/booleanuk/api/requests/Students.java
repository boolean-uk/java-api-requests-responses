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

    @GetMapping("/{firstName}")
    public Student getSpecificStudent(@PathVariable String firstName) {
        for(Student student : this.students)
        {
            if(student.getFirstName().equals(firstName))
                return student;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student update(@PathVariable String firstName, @RequestBody Student student) {
        for(Student s : this.students)
        {
            if(s.getFirstName().equals(firstName)) {
                s.setFirstName(student.getFirstName());
                s.setLastName(student.getLastName());
                return s;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{firstName}")
    public Student delete(@PathVariable String firstName) {
        int i = 0;
        for(Student s : this.students)
        {
            if(s.getFirstName().equals(firstName)) {
                return this.students.remove(i);
            }
            i++;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
