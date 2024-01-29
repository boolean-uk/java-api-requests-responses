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

    @DeleteMapping("/remove/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Student delete(@PathVariable int id) {
        Student localStudent = null;
        for(Student student : this.students) {
            if (student.getID() == id) {
                localStudent = student;
                this.students.remove(student);
                break;
            }
        }
        return localStudent;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Student getStudentBySID(@PathVariable int id) {
        return this.students.stream()
                .filter(student -> student.getID() == id)
                .findFirst()
                .orElse(null);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student update(@PathVariable int id, @RequestBody Student s) {
        Student localStudent = this.students.stream()
                .filter(student -> student.getID() == id)
                .findFirst()
                .orElse(null);
        if (localStudent != null) {
            localStudent.setFirstName(s.getFirstName());
            localStudent.setLastName(s.getLastName());
            return localStudent;
        }
        return null;
    }
}
