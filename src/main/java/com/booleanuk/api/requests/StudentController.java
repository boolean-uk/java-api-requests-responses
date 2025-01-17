package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {
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

    @GetMapping("/{name}")
    public Student getOne(@PathVariable String name) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getFirstName().equals(name)) {
                return students.get(i);
            }
        }
        return null;
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student update(@PathVariable String name, @RequestBody Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getFirstName().equals(name)) {
                students.get(i).setFirstName(student.getFirstName());
                students.get(i).setLastName(student.getLastName());
                return student;
            }
        }
        return null;
    }

    @DeleteMapping("/{name}")
    public Student delete(@PathVariable String name) {
        Student deletedStudent;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getFirstName().equals(name)) {
                deletedStudent = students.get(i);
                students.remove(students.get(i));
                return deletedStudent;
            }
        }
        return null;
    }

}
