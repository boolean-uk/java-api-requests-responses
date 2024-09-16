package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class Students {
    private final List<Student> students = new ArrayList<>(){{
        add(new Student("Nathan", "King"));
        add(new Student("Dave", "Ames"));
    }};

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@RequestBody Student student) {
        students.add(student);
        return student;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return students;
    }

    @GetMapping("/{firstName}")
    public Student getStudentByName(@PathVariable String firstName) {
        final int _studentIndex = findStudentByName(firstName);
        return _studentIndex >= 0 ? students.get(_studentIndex) : null;
    }

    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student updateByName(@PathVariable String firstName, @RequestBody Student student) {
        final int _studentIndex = findStudentByName(firstName);
        if (_studentIndex < 0) return null;

        students.get(_studentIndex).firstName = student.firstName;
        students.get(_studentIndex).lastName = student.lastName;

        return students.get(_studentIndex);
    }

    @DeleteMapping("/{firstName}")
    public Student removeStudentByName(@PathVariable String firstName) {
        final int _studentIndex = findStudentByName(firstName);
        return _studentIndex >= 0 ? students.remove(_studentIndex) : null;
    }

    private int findStudentByName(final String firstName) {
        for (int i = 0; i < students.size(); i++)
            if (students.get(i).firstName.equals(firstName))
                return i;
        return -1;
    }
}
