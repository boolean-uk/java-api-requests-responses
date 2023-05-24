package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @GetMapping
    public List<Student> getAll() {
        return this.students;
    }

    @GetMapping("/{firstName}")
    public ResponseEntity<Student> getOne(@PathVariable String firstName){
        for (Student student : students) {
            if (Objects.equals(student.getFirstName(),firstName)) {
                return new ResponseEntity<>(student,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{firstName}")
    public ResponseEntity<Student> deleteStudent(@PathVariable String firstName){
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (Objects.equals(student.getFirstName(), firstName)) {
                return new ResponseEntity<>(students.remove(i),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{firstName}")
    public ResponseEntity<Student> updateStudent(@PathVariable String firstName,@RequestBody Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (Objects.equals(student.getFirstName(), firstName)) {
                students.get(i).setFirstName(updatedStudent.getFirstName());
                students.get(i).setLastName(updatedStudent.getLastName());
                return new ResponseEntity<>(students.get(i),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
}
