package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("students")
public class Students {
    private ArrayList<Student> students;

    public Students(){
        students = new ArrayList<>();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@RequestBody Student student) {
        this.students.add(student);
        return student;
    }

    @GetMapping
    public ArrayList<Student> getAll() {
        return this.students;
    }

    @GetMapping("/{name}")
    public Student getOneStudent(@PathVariable String name){
        for(Student s : this.students){
            if(s.getFirstName().equals(name)){
                return s;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student updateOneStudent(@PathVariable String name, @RequestBody Student student){
        if(student.getFirstName() == null && student.getLastName() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        for(Student s : this.students){
            if(s.getFirstName().equals(name)){
                s.setFirstName(student.getFirstName());
                s.setLastName(student.getLastName());
                return s;
            }
        }
        throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{name}")
    public Student deleteOneStudent(@PathVariable String name){

        Iterator<Student> i = this.students.iterator();
        while(i.hasNext()){
            Student student = i.next();
            if(student.getFirstName().equals(name)){
                i.remove();
                return student;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
