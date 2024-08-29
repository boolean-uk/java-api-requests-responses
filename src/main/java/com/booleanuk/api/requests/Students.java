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
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Student> getAll() {
        return this.students;
    }

    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.FOUND)
    public Student getStudentWithName(@PathVariable String name) {
        for (Student student: students) {
            if (student.getFirstName().equals(name)) {
                return student;
            }
        }
        return null;
    }

    @DeleteMapping("{name}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Student removeStudentWithName(@PathVariable String name) {
        for(Student student: students) {
            if(student.getFirstName().equals(name)) {
                students.remove(student);
                return student;
            }
        }
            return null;
    }

    @PutMapping("{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student editStudent(@PathVariable String name, @RequestBody Student insertStudent) {
        for(int i = 0; i < students.size(); i++){
            if(students.get(i).getFirstName().equals(name)) {
                students.set(i, insertStudent);
                return students.get(i);
            }
        }
        return  null;
    }
}
