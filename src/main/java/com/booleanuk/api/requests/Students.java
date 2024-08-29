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

    @GetMapping("{firstName}")
    public Student getAStudent(@PathVariable String firstName) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getFirstName().equals(firstName)) {
                return students.get(i);
            }
        }
        return null;
    }

    @PutMapping("{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student updateStudent(@PathVariable String firstName, @RequestBody Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getFirstName().equals(firstName)) {
                students.get(i).setFirstName(student.getFirstName());
                students.get(i).setLastName(student.getLastName());
                return students.get(i);
            }
        }
        return null;
    }

    @DeleteMapping("{firstName}")
        public Student deleteStudent (@PathVariable String firstName){
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getFirstName().equals(firstName)) {
                    return students.remove(i);
                }
            }
        return null;
    }
}
