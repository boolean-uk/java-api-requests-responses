package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
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
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getAll() {
        return this.students;
    }

    @GetMapping("/{firstName}")
    @ResponseStatus(HttpStatus.OK)
    public Student getStudent(@PathVariable(name = "firstName") String firstName) {
        for (Student s : students) {
            if (s.getFirstName().equals(firstName)) {
                return s;
            }
        }
        return null;
    }

    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student updateStudent(
            @PathVariable(name = "firstName") String firstName,
            @RequestBody Student newStudent) {
        for (int i=0; i<students.size(); i++) {
            if (students.get(i).getFirstName().equals(firstName)) {
                students.set(i, newStudent);
                return students.get(i);
            }
        }
        return null;
    }

    @DeleteMapping("/{firstName}")
    @ResponseStatus(HttpStatus.OK)
    public Student deleteStudent(@PathVariable(name = "firstName") String firstName) {
        boolean foundStudent = false;
        int i;
        for (i = 0; i < students.size(); i++) {
            if (students.get(i).getFirstName().equals(firstName)) {
                foundStudent = true;
                break;
            }
        }
        if (foundStudent) {
            return students.remove(i);
        }
        return null;
    }
}
