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


    // Getting a specific student
    @GetMapping("/{firstName}")
    public Student getSpecificStudent(@PathVariable String firstName){
        for(Student student : this.students){
            if(student.getFirstName().equals(firstName)){
                return student;
            }
        }
        return null;
    }

    // Updating a student
    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student updateSpecificStudent(@PathVariable String firstName, @RequestBody Student student){
        for(Student aStudent : this.students){
            if(aStudent.getFirstName().equals(firstName)){
                aStudent.setFirstName(student.getFirstName());
                aStudent.setLastName(student.getLastName());
                return student;
            }
        }
        return null;
    }


    // Deleting a student
    @DeleteMapping("/{firstName}")
    public Student deleteSpecificStudent(@PathVariable String firstName){
        for(Student aStudent : this.students){
            if(aStudent.getFirstName().equals(firstName)){
                this.students.remove(aStudent);
                return aStudent;
            }
        }
        return null;
    }
}
