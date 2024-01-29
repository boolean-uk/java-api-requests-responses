package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentsController {

    private List<Student> students = new ArrayList<>(){{
        add(new Student("Nathan", "King"));
        add(new Student("Dave", "Ames"));
    }};

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student newStudent) {
        students.add(newStudent);
        return newStudent;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return students;
    }

    @GetMapping("/{firstName}")
    public Student getSpecificStudent(@PathVariable String firstName) {
        return students.stream()
                .filter(student -> student.getFirstName().equals(firstName))
                .findFirst()
                .orElse(null);
    }

    @PutMapping("/{firstName}")
    public Student updateStudent(@PathVariable String firstName, @RequestBody Student updatedStudent) {
        Student studentToUpdate = students.stream()
                .filter(student -> student.getFirstName().equals(firstName))
                .findFirst()
                .orElse(null);

        if (studentToUpdate != null) {
            studentToUpdate.setFirstName(updatedStudent.getFirstName());
            studentToUpdate.setLastName(updatedStudent.getLastName());
        }

        return studentToUpdate;
    }

    @DeleteMapping("/{firstName}")
    public Student deleteStudent(@PathVariable String firstName) {
        Student studentToDelete = students.stream()
                .filter(student -> student.getFirstName().equals(firstName))
                .findFirst()
                .orElse(null);

        if (studentToDelete != null) {
            students.remove(studentToDelete);
        }

        return studentToDelete;
    }
}
