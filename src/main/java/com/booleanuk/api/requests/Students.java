package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class Students {
    private final List<Student> studentList;

    public Students(){
        this.studentList = new ArrayList<>();
        this.studentList.add(new Student("Nathan", "King"));
        this.studentList.add(new Student("Dave", "Ames"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@RequestBody Student student) {
        this.studentList.add(student);
        return student;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getAll() {
        return this.studentList;
    }

    @GetMapping("/{firstName}")
    @ResponseStatus(HttpStatus.OK)
    public Student get(@PathVariable String firstName){
        return getStudent(firstName);
    }

    // Helper function to adhere to DRY and increase readability
    private Student getStudent(String firstName){
        return this.studentList.stream()
                .filter(s -> s.getFirstName().equalsIgnoreCase(firstName))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }

    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student updateStudent(@PathVariable String firstName, @RequestBody Student student){
        Student existingStudent = getStudent(firstName);
        existingStudent.setLastName(student.getLastName());
        return existingStudent;
    }

    @DeleteMapping("/{firstName}")
    @ResponseStatus(HttpStatus.OK)
    public Student delete(@PathVariable String firstName){
        Student existingStudent = getStudent(firstName);
        this.studentList.remove(existingStudent);
        return existingStudent;
    }
}

