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
    public Student getNameOfAStudent(@PathVariable String firstName){
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
        for (Student oldStudent : students){
            if (firstName.equalsIgnoreCase(oldStudent.getFirstName())){
                oldStudent.setFirstName(student.getFirstName());
                oldStudent.setLastName(student.getLastName());
                return student;
            }
        }
        return null;
    }

    @DeleteMapping("/{firstName}")
    public Student deleteStudent(@PathVariable String firstName){
        for (Student oldStudent : students){
            if (oldStudent.getFirstName().equalsIgnoreCase(firstName)){
                int i = students.indexOf(oldStudent);
                return this.students.remove(i);
            }
        }
        return null;
    }
}
