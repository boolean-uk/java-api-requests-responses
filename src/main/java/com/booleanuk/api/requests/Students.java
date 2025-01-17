package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class Students {
    private List<Student> students = new ArrayList<>() {
        {
            add(new Student("Nathan", "King"));
            add(new Student("Dave", "Ames"));
        }
    };

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
        for (Student student : students) {
            if (student.getFirstName().toLowerCase().equals(firstName.toLowerCase())) {
                return student;
            }
        }

        return null;
    }

    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student updateStudent(@PathVariable String firstName, @RequestBody Student updated) {
        Student student = getStudent(firstName);

        // did not find student with firstName
        if (student == null) {
            return create(updated);
        } else {
            // update existing student
            student.setFirstName(updated.getFirstName());
            student.setLastName(updated.getLastName());
        }
        return updated;
    }

    @DeleteMapping("/{firstName}")
    public Student deleteStudent(@PathVariable String firstName) {
        Student student = getStudent(firstName);
        if (student == null) {
            return null;
        }
        students.remove(student);
        return student;
    }
}
