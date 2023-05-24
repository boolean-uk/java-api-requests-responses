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

    @GetMapping("/{firstname}")
    public Student getStudent(@PathVariable (name = "firstname") String firstname) {
        for (Student student : students) {
            if (student.getFirstName().equals(firstname)) {
                return student;
            }
        }
        return null;
    }

    @PutMapping("/{firstname}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student updateStudent(@PathVariable (name = "firstname") String firstname, @RequestBody Student student) {
        for (Student indexStudent : students) {
            if (indexStudent.getFirstName().equals(firstname)) {
                int index = this.students.indexOf(indexStudent);
                this.students.get(index).setFirstName(student.getFirstName());
                this.students.get(index).setLastName(student.getLastName());
                return this.students.get(index);
            }
        }
        return null;
    }

    @DeleteMapping("/{firstname}")
    public Student deleteStudent(@PathVariable (name = "firstname") String firstname) {
        for (Student student : students) {
            if (student.getFirstName().equals(firstname)) {
                int index = this.students.indexOf(student);
                return this.students.remove(index);
            }
        }
        return null;
    }

}
