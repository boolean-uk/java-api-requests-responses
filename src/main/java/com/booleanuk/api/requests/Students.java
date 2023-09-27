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
    public Student getSpecificStudent(@PathVariable(name = "firstName") String firstName)
    {
        for (Student stu : students)
        {
            if (stu.getFirstName().equals(firstName))
            {
                return stu;
            }
        }
        return null;
    }

    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student update(@PathVariable(name = "firstName") String firstName, @RequestBody Student student) {
        for (Student stu : students) {
            if (stu.getFirstName().equals(firstName)) {
                stu.setFirstName(student.getFirstName());
                stu.setLastName(student.getLastName());
                return stu;
            }
        }
        return null;
    }

    @DeleteMapping("/{firstname}")
    public Student delete(@PathVariable String firstname) {
        int id = 0;
        for (Student stu : students) {
            if (stu.getFirstName().equals(firstname)) {
                id = students.indexOf(stu);
            }
        }
        this.students.remove(id);
        return null;
    }
}
