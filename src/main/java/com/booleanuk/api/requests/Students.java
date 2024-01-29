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
    @ResponseStatus(HttpStatus.OK)
    public Student getOne(@PathVariable (name = "firstName") String firstName) {
        for (Student student : students) {
            if(student.getFirstName().equalsIgnoreCase(firstName)) {
                return student;
            }
        }
        return null;
    }

    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student update(@PathVariable(name = "firstName") String firstName, @RequestBody Student updatedStudent) {
        for(Student student : students) {
            if(student.getFirstName().equalsIgnoreCase(firstName)) {
                student.setFirstName(updatedStudent.getFirstName());
                student.setLastName(updatedStudent.getLastName());
                return student;
            }
        }
        return null;
    }

    @DeleteMapping("/{firstName}")
    @ResponseStatus(HttpStatus.OK)
    public Student delete(@PathVariable (name = "firstName") String firstname) {
        for(Student student : students) {
            if(student.getFirstName().equalsIgnoreCase(firstname)) {
                students.remove(student);
                return student;
            }
        }
        return null;
    }
}
