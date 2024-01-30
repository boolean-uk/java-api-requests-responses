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
    public Student getStudent(@PathVariable String firstName) {
        for(int i = 0; i < students.size(); i++) {
            if (students.get(i).getFirstName().equals(firstName)) {
                return this.students.get(i);
            }
        }
        return null;
    }

    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student update(@PathVariable String firstName, @RequestBody Student student) {
        Student updateStudent = getStudent(firstName);
        if(updateStudent != null) {
            updateStudent.setFirstName(student.getFirstName());
            updateStudent.setLastName(student.getLastName());
            return updateStudent;
        }
        return null;
    }

    @DeleteMapping("/{firstName}")
    public Student delete(@PathVariable String firstName) {
        Student deleteStudent = getStudent(firstName);
        if (deleteStudent != null) {
            this.students.remove(deleteStudent);
            return deleteStudent;
        }
        return null;
    }
}
