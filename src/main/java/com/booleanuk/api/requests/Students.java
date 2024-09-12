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

    @GetMapping("{name}")
    public Student getSpecificStudent(@PathVariable(name = "name") String name) {
        for (Student student : this.students) {
            if (student.getFirstName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null;
    }

    @PutMapping("{name}")
    public Student updateStudent(@PathVariable(name = "name") String name, @RequestBody Student student) {
        for (Student object : this.students) {
            if (name.equalsIgnoreCase(object.getFirstName())) {
                object.setFirstName(student.getFirstName());
                object.setLastName(student.getLastName());
                return student;
            }
        }
        return null;
    }

    @DeleteMapping("{name}")
    public Student deleteStudent(@PathVariable(name = "name") String name) {
        for (int i = 0; i < this.students.size(); i++) {
            if (name.equalsIgnoreCase(this.students.get(i).getFirstName())) {
                return this.students.remove(i);
            }
        }
        return null;
    }
}
