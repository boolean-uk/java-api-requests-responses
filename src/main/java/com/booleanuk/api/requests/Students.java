package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student update(@RequestBody Student student, @PathVariable (name="firstName") String firstName) {
        for (Student stu : this.students) {
            if (stu.getFirstName().equals(firstName)) {
                stu.setFirstName(student.getFirstName());
                stu.setLastName(student.getLastName());
                return stu;
            }
        }

        return null;
    }

    @GetMapping
    public List<Student> getAll() {
        return this.students;
    }

    @GetMapping("/{firstName}")
    public Student getStudent(@PathVariable (name="firstName") String firstName) {

        Optional<Student> optionalStudent = students.stream()
                .filter(student -> student.getFirstName().equals(firstName))
                .findFirst();

        return optionalStudent.orElse(null);
    }

    @DeleteMapping("/{firstName}")
    public Student deleteStudent(@PathVariable (name="firstName") String firstName) {

        for (Student stu : this.students) {
            if (stu.getFirstName().equals(firstName)) {
                this.students.remove(stu);
                return stu;
            }
        }

        return null;
    }
}
