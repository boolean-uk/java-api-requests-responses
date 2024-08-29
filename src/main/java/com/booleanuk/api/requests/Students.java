package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class Students {
    private List<Student> students = new ArrayList<>() {{
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
    public Student getStudent(@PathVariable String name) {
        for (Student s : this.students) if (s.getFirstName().equals(name)) return s;
        return null;
    }

    @PutMapping("{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student update(@PathVariable String name, @RequestBody Student student) {
        for (int i = 0; i < this.students.size(); ++i) {
            if (this.students.get(i).getFirstName().equals(name)) {
                this.students.get(i).setFirstName(student.getFirstName());
                this.students.get(i).setLastName(student.getLastName());
                return this.students.get(i);
            }
        }

        return null;
    }

    @DeleteMapping("{name}")
    public Student delete(@PathVariable String name) {
        for (int i = 0; i < this.students.size(); ++i) {
            if (this.students.get(i).getFirstName().equals(name)) return this.students.remove(i);
        }
        return null;
    }

}
