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
        add(new Student("Ali", "Keskin"));

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
    public Student getOne(@PathVariable(name = "firstName") String firstName) {
        String urlLower = firstName.toLowerCase();
        for (Student stu : students) {
            String apiLower = stu.getFirstName().toLowerCase();
            if (apiLower.equals(urlLower)) {
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

    @DeleteMapping("/{firstName}")
    public Student delete(@PathVariable(name = "firstName") String firstName) {
        //Never delete an item directly using a loop, add it to a var instead
        //Then delete the added var later on with an if statement
        Student studentToRemove = null;

        for (Student stu : students) {
            if (stu.getFirstName().equals(firstName)) {
                studentToRemove = stu;
                break;
            }
        }

        if (studentToRemove != null) {
            students.remove(studentToRemove);
            return studentToRemove;
        } else {
            return null;
        }
    }

}
