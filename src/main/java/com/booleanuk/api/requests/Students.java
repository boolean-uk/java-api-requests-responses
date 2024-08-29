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

    @GetMapping
    public List<Student> getAll() {
        return this.students;
    }

    @GetMapping("{firstName}")
    public Student getOne(@PathVariable String firstName){
        for (Student student : students){
            if(student.getFirstName().equals(firstName)){
                return student;
            }
        }
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@RequestBody Student student) {
        this.students.add(student);
        return student;
    }



    @PutMapping("{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student update(@PathVariable String name, @RequestBody Student requestStudent) {
        for(int i = 0; i < students.size(); i++){
            if (students.get(i).getFirstName().equals(name)){
                this.students.set(i, requestStudent);
                return students.get(i);
            }
        }
        return null;
    }

    @DeleteMapping("{name}")
    public Student delete(@PathVariable String name) {
        for (Student student : students){
            if (student.getFirstName().equals(name)){
                students.remove(student);
                return student;

            }
        }
        return null;
    }
}


