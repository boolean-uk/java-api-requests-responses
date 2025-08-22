package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class Students {
    private List<Student> students = new ArrayList<>() {{
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
    public List<Student> getStudents() {

        return this.students;
    }

    @GetMapping("/{firstName}")
    public Student getSpecificStudent(@PathVariable String firstName) {

        Student foundStudent = students.stream()
                .filter(student -> student.getFirstName().equals(firstName))
                .findFirst()
                .orElse(null);
        return foundStudent;
    }
    @PutMapping("/{firstName}")
    public Student updateStudent(@PathVariable String firstName, @RequestBody Student student) {

        Student foundStudent = students.stream()
                .filter(s -> s.getFirstName().equals(firstName))
                .findFirst()
                .orElse(null);
        if (foundStudent != null) {
            foundStudent.setFirstName(student.getFirstName());
            foundStudent.setLastName(student.getLastName());
        }
        return foundStudent;
    }
    @DeleteMapping("/{firstName}")
    public Student deleteStudent(@PathVariable String firstName) {
        Student foundStudent = students.stream()
                .filter(s -> s.getFirstName().equals(firstName))
                .findFirst()
                .orElse(null);
        if (foundStudent != null) {
            students.remove(foundStudent);
        }
        return foundStudent;
    }
}
