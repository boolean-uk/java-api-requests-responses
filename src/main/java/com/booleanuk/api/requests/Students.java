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

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Student getStudent(@PathVariable String name){

        return this.students.stream()
                .filter(student -> student.getFirstName().equalsIgnoreCase(name))
                .findAny()
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found!"));

    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Student update(@PathVariable String name, @RequestBody Student updatedStudent){

        Student student = update(name);

        student.setFirstName(updatedStudent.getFirstName());
        student.setLastName(updatedStudent.getLastName());

        return student;

    }


    public Student update(String name){

        return students.stream()
                .filter(s -> s.getFirstName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found!"));
    }



    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Student deleteStudent(@PathVariable String name){

        Student student = delete(name);
        this.students.remove(student);

        return student;
    }


    public Student delete(String name){
            return students.stream()
                    .filter(s -> s.getFirstName().equalsIgnoreCase(name))
                    .findFirst()
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found!"));
        }

}
