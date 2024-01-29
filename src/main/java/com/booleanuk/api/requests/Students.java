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

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping
    public List<Student> getAll() {
        return this.students;
    }


    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/{firstName}")
    public Student getStudentByFirstName(@PathVariable String firstName) {
        for(Student student : this.students) {
            if(student.getFirstName().equalsIgnoreCase(firstName)) {
                return student;
            }
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found by provided first name");
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{firstName}")
    public Student updateStudentByFirstName(@PathVariable String firstName,@RequestBody Student student) {
        for(Student stud : this.students) {
            if(stud.getFirstName().equalsIgnoreCase(firstName)) {
                stud.setFirstName(student.getFirstName());
                stud.setLastName(student.getLastName());
                return stud;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found by provided first name");

    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{firstName}")
    public Student deleteStudentByFirstName(@PathVariable String firstName) {

        for(Student stud : this.students) {
            if(stud.getFirstName().equalsIgnoreCase(firstName)) {
                this.students.remove(stud);
                return stud;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found by provided first name");

    }

}
