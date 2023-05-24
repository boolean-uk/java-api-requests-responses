package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class Students {
    private List<Student> students = new ArrayList<>(){{
        add(new Student("Dennis", "Voutos"));
        add(new Student("Konstantinos", "Vroustouris"));
    }};

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@RequestBody Student student) {
        this.students.add(student);
        return student;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getAll() {
        return this.students;
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Student getSpecificStudent(@PathVariable (name = "name") String name){
        for(Student student:this.students){
            if(student.getFirstName().equalsIgnoreCase(name)){
                return student;
            }
        }
        return null;
    }
    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student update(@RequestBody Student student, @PathVariable (name = "name") String name){
        for(Student temp :this.students){
            if(temp.getFirstName().equalsIgnoreCase(name)){
                temp.setFirstName(student.getFirstName());
                temp.setLastName(student.getLastName());
                return student;
            }
        }
        return null;
    }
    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Student delete(@PathVariable (name = "name") String name){
        for(Student student:this.students){
            if(student.getFirstName().equalsIgnoreCase(name)){
                return this.students.remove(this.students.indexOf(student));
            }
        }
        return null;
    }
}
