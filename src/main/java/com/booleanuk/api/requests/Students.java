package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("students")
public class Students {
    private List<Student> students = new ArrayList<>(){{

    }};

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@RequestBody Student student) {
        this.students.add(student);

        return student;
    }

    @DeleteMapping("/{firstName}")
    @ResponseStatus(HttpStatus.OK)
    public Student delete(@PathVariable String firstName) {
        Student studentToRemove = this.students.stream()
                .filter(student -> student.getFirstName().equals(firstName))
                .findFirst()
                .orElse(null);

        if (studentToRemove != null) {
            this.students.remove(studentToRemove);
        }

        return studentToRemove;
    }

    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.OK)
    public Student put(@PathVariable String firstName, @RequestBody Student updatedStudent) {
        Iterator<Student> iterator = this.students.iterator();

        while (iterator.hasNext()) {
            Student existingStudent = iterator.next();
            if (existingStudent.getFirstName().equals(firstName)) {
                existingStudent.setFirstName(updatedStudent.getFirstName());
                existingStudent.setLastName(updatedStudent.getLastName());
                return existingStudent;
            }
        }

        return null;
    }

    @GetMapping
    public List<Student> getAll() {
        return this.students;
    }

    @GetMapping("/{firstName}")
    @ResponseStatus(HttpStatus.OK)
    public Student getStudent(@PathVariable(name = "firstName") String firstName) {
        return this.students.stream()
                .filter(student -> student.getFirstName().equals(firstName))
                .findFirst()
                .orElse(null);
    }
}
