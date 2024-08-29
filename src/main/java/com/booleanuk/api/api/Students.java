package com.booleanuk.api.api;

import com.booleanuk.api.Response;
import com.booleanuk.api.requests.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class Students {
    private final List<Student> students = new ArrayList<>(){{
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
    @ResponseStatus(HttpStatus.OK)
    public Object getStudentByFirstName(@PathVariable String firstName) {
        Student student = null;
        for (Student item : this.students) {
            if (item.firstName().equals(firstName)) {
                student = item;
                break;
            }
        }
        return student != null ? student : Response.notFound("No student with named " + firstName + " exists");
    }

    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Object updateStudent(@PathVariable String firstName, @RequestBody Student body) {
        boolean exists = this.students.stream().anyMatch(s -> s.firstName().equals(firstName));
        if (!exists) {
            return Response.notFound("No student named " + firstName + " exists");
        }

        Student updatedStudent = null;
        for (Student item : this.students) {
            if (item.firstName().equals(firstName)) {
                this.students.remove(item);

                updatedStudent = new Student(body.firstName(), body.lastName());
                this.students.add(updatedStudent);
                break;
            }
        }
        return updatedStudent;
    }

    @DeleteMapping("/{firstName}")
    @ResponseStatus(HttpStatus.OK)
    public Object deleteStudent(@PathVariable String firstName) {
        boolean exists = this.students.stream().anyMatch(s -> s.firstName().equals(firstName));
        if (!exists) {
            return Response.notFound("No student named " + firstName + " exists");
        }

        Student deletedStudent = null;
        for (Student item : this.students) {

            if (item.firstName().equals(firstName)) {
                deletedStudent = item;
                this.students.remove(item);
                break;

            }
        }

        return deletedStudent;
    }
}
