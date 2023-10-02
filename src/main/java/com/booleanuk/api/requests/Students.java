package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class Students {

    private List<Student> students = new ArrayList<>();

    public Students() {
        students.add(new Student("Nathan", "King"));
        students.add(new Student("Dave", "Ames"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@RequestBody Student student) {
        students.add(student);
        return student;
    }

    @GetMapping
    public List<Student> getAll() {
        return students;
    }

    @GetMapping("/{firstName}")
    public Student getStudent(@PathVariable("firstName") String firstName) {
        for (Student student : students) {
            if (student.getFirstName().equals(firstName)) {
                return student;
            }
        }
        return null;
    }

    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student updateStudent(@PathVariable("firstName") String firstName, @RequestBody Student updatedStudent) {
        for (Student student : students) {
            if (student.getFirstName().equals(firstName)) {
                student.setFirstName(updatedStudent.getFirstName());
                student.setLastName(updatedStudent.getLastName());
                return student;
            }
        }
        return null;
    }

    @DeleteMapping("/{firstName}")
    public Student deleteStudent(@PathVariable("firstName") String firstName) {
        for (Student student : students) {
            if (student.getFirstName().equals(firstName)) {
                students.remove(student);
                return student;
            }
        }
        return null;
    }
}
