package com.booleanuk.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("/student")
public class StudentController {

    private ArrayList<Student> students;

    public StudentController(ArrayList<Student> students) {
        this.students = students;
    }

    @GetMapping
    public ArrayList<Student> getStudents() {
        return students;
    }

    @GetMapping("/{firstname}")
    public Student getByFirstname(@PathVariable String firstname) {
        for (Student st : students) {
            if (st.getFirstname().equals(firstname)) {
                return st;
            }
        }
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@RequestBody Student student) {
        if (!students.contains(student)) {
            if (student.getFirstname() != null && student.getLastname() != null) {
                this.students.add(student);
                return student;
            }
        }
        return null;
    }

    @PutMapping("/{firstname}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student update(@PathVariable String firstname, @RequestBody Student student) {
        for (Student st : students) {
            if (st.getFirstname().equals(firstname)) {
                st.setFirstname(student.getFirstname());
                st.setLastname(student.getLastname());
                return st;
            }
        }
        return null;
    }

    @DeleteMapping("/{firstname}")
    public Student delete(@PathVariable String firstname) {
        int id = 0;
        for (Student st : students) {
            if (st.getFirstname().equals(firstname)) {
                id = students.indexOf(st);
            }
        }
        this.students.remove(id);
        return null;
    }
}
