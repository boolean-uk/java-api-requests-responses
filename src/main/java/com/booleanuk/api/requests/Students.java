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

    @GetMapping("/{firstName}")
    public Student getSpesificStudent(@PathVariable String firstName){
        for (Student student : students){
            if (student.getFirstName().equals(firstName)){
                return student;
            }
        }
        return null;
    }

    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student updateStudent(@PathVariable String firstName, @RequestBody Student student){

        for (Student curStudent : students ){
            if (curStudent.getFirstName().equals(firstName)){
                int index = students.indexOf(curStudent);
                this.students.get(index).setFirstName(student.getFirstName());
                this.students.get(index).setLastName(student.getLastName());
                return this.students.get(index);
            }
        }
        return null;
    }

    @DeleteMapping("/{firstName}")
    public Student deleteStudent(@PathVariable String firstName){
        for (Student student : students){
            if (student.getFirstName().equals(firstName)){
                int index = students.indexOf(student);
                return this.students.remove(index);
            }
        }
        return null;
    }

}
