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
    public Student getOne(@PathVariable (name = "firstName") String firstName){
        int index = -1;
        for (Student student: students) {
            if (student.getFirstName().equalsIgnoreCase(firstName)) {
                index = this.students.indexOf(student);
            }
        }
        if (index > 0) {
            return this.students.get(index);
        }
        return null;
    }

    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student updated(@PathVariable (name = "firstName") String firstName,@RequestBody Student student){
        int index = -1;
        for (Student st: students){
            if (st.getFirstName().equalsIgnoreCase(firstName)){
                index = this.students.indexOf(st);
            }
        }
        if (index >= 0){
            this.students.get(index).setFirstName(student.getFirstName());
            this.students.get(index).setLastName(student.getLastName());
            return this.students.get(index);
        }
        return null;
    }

    @DeleteMapping("/{firstName}")
    public Student delete(@PathVariable (name = "firstName") String firstName){
        int index = -1;
        for (Student student: students){
            if (student.getFirstName().equalsIgnoreCase(firstName)) {
                index = this.students.indexOf(student);
            }
        }
        if (index > 0){
            return this.students.remove(index);
        }
        return null;
    }
}
