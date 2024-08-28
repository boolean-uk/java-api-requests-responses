package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class Students {
    private List<Student> students = new ArrayList<>(){{
        add(new Student("Nathan", "King"));
        add(new Student("Dave", "Ames"));
    }};

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        this.students.add(student);

        return student;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getStudents() {
        return this.students;
    }

    @GetMapping("/{firstName}")
    @ResponseStatus(HttpStatus.OK)
    public Student getSpecificStudent(@PathVariable String firstName) {
        return findStudent(firstName);
    }

    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student updateStudent(@PathVariable String firstName, @RequestBody Student updatedStudent) {
        for(Student student : this.students) {
            if(student.getFirstName().equalsIgnoreCase(firstName)) {
                student.setFirstName(updatedStudent.getFirstName());
                //student.setLastName(updatedStudent.getLastName());
                return student;
            }
        }
        return null;
    }

    @DeleteMapping("/{firstName}")
    @ResponseStatus(HttpStatus.OK)
    public Student deleteStudent(@PathVariable String firstName) {
        Student studentToDelete = findStudent(firstName);

        if(studentToDelete != null) {
            this.students.remove(studentToDelete);
        }

        return studentToDelete;
    }

    private Student findStudent(String name) {
        return this.students.stream()
                .filter(student -> student.getFirstName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}