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
    public Student getOne(@PathVariable ( name = "firstName" )String name) {
        for (Student student: students) {
            if (name.equals(student.getFirstName())){
                return student;
            }
        }
        return null;
    }
    @PutMapping("/firstName")
    @ResponseStatus(HttpStatus.CREATED)
    public Student update(@PathVariable ( name = "firstName") String name, @RequestBody Student student) {
        for (Student st : students) {
            if (name.equals(st.getFirstName())) {
                st.setFirstName(st.getFirstName());
                st.setLastName(st.getLastName());
                return st;
            }
        } return null;
    }
    @DeleteMapping("/{firstName}")
    public Student delete(@PathVariable (name = "firstName") String name) {
        for (Student student : students) {
            if (name.equals(student.getFirstName())){
                this.students.remove(student);
                return student;
            }
        }
        return null;
    }
}
