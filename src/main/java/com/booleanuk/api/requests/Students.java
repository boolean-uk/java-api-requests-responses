package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class Students {
    private ArrayList<Student> students;
    public Students(){
        this.students = new ArrayList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@RequestBody Student student) {
        this.students.add(student);
        return student;
    }

    @GetMapping
    public  List<Student> getAll(){
        return this.students;
    }

    @GetMapping("/{firstName}")
    public Student getOne(@PathVariable String firstName){
        for (Student student : students){
            if(student.getFirstName().equalsIgnoreCase(firstName)){
                return student;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with first name: " + firstName);
    }

    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student update(@PathVariable String firstName, @RequestBody Student student){
        for (Student s : this.students){
            if(s.getFirstName().equalsIgnoreCase(firstName)){
                s.setFirstName(student.getFirstName());
                s.setLastName(student.getLastName());
                return s;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with first name: " + firstName);
    }


    @DeleteMapping("/{firstName}")
    public Student delete(@PathVariable String firstName) {
        for (Student student : students){
            if(student.getFirstName().equalsIgnoreCase(firstName)){
                students.remove(student);
                return student;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with first name: " + firstName);
    }

}
