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
        add(new Student("Melvin", "Seelan"));
        add(new Student("Ibby", "Secka"));
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
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Student getStudentByFirstName(@PathVariable String firstName) {
        List<Student> allStudents = getAll();
        for (Student student : allStudents) {
            if (student.getFirstName().equalsIgnoreCase(firstName)) {
                return student;
            }
        }
        return null;

    }

    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Student updateStudent(@PathVariable String firstName, @RequestBody Student updateStudent) {
        List<Student> allStudents = getAll();

        for (Student student : allStudents) {
            if (student.getFirstName().equalsIgnoreCase(firstName)) {
                student.setFirstName(updateStudent.getFirstName());
                student.setLastName(updateStudent.getLastName());
                return student;
            }
        }

        return null;
    }

    @DeleteMapping("/{firstName}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Student deleteStudent(@PathVariable String firstName) {
        List<Student> allStudents = getAll();

        allStudents.removeIf(student -> student.getFirstName().equalsIgnoreCase(firstName));

        return null;
    }


}
