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

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getAll() {
        return this.students;
    }

    @GetMapping("/{firstName}")
    @ResponseStatus(HttpStatus.OK)
    public Student getSpecificStudent(@PathVariable String firstName){
        return findStudentByName(firstName);
    }

    @PutMapping("{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student update(@PathVariable String name, @RequestBody Student student){
        for(Student s: students){
            if(name.equals(s.getFirstName())){
                s.setFirstName(student.getFirstName());
                s.setLastName(student.getLastName());
                return student;
            }
        }
        return null;
    }

    @DeleteMapping("{name}")
    public Student delete(@PathVariable String name){
        for (Student s: students){
            if (s.getFirstName().equals(name)){
                students.remove(s);
                return s;
            }
        }
        return null;
    }



    public Student findStudentByName(String firstName){
        for(Student s: students){
            if(s.getFirstName().equals(firstName)){
                return s;
            }
        }
        return null;
    }
}
