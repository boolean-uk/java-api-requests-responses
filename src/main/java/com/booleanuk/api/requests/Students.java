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
    public Student getOne(@PathVariable (name = "firstName") String firstName) {
        for(Student student: students) {
            if(student.getFirstName().equalsIgnoreCase(firstName)) {
                return student;
            }
        }
        return null;
    }

    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student update(@PathVariable String firstName, @RequestBody Student student) {
        for(Student currentStudent: students) {
            if(currentStudent.getFirstName().equalsIgnoreCase(firstName)) {
                currentStudent.setFirstName(student.getFirstName());
                currentStudent.setLastName(student.getLastName());
                return currentStudent;
            }
        }
        return null;
    }

    @DeleteMapping("/{firstName}")
    public Student delete(@PathVariable String firstName) {
        for(int i = 0; i < students.size(); i++) {
            if(students.get(i).getFirstName().equalsIgnoreCase(firstName)) {
                return students.remove(i);
            }
        }
        return null;
    }
}
