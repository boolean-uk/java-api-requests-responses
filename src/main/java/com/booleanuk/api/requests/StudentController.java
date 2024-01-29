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
    public Student createStudent(@RequestBody Student student) {
        this.students.add(student);

        return student;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return this.students;
    }

    @GetMapping("/{firstName}")
    public Student getOneStudent(@PathVariable String firstName){
        return getStudent(firstName);
    }

    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student updateStudent(@PathVariable String firstName, @RequestBody Student updatedStudent){
        Student student = getStudent(firstName);

        if (student != null){
            int index = students.indexOf(student);
            students.get(index).setFirstName(updatedStudent.getFirstName());
            students.get(index).setLastName(updatedStudent.getLastName());

            return students.get(index);
        }
        return null;
    }

    @DeleteMapping("/{firstName}")
    public Student deleteStudent(@PathVariable String firstName){
        Student student = getStudent(firstName);

        if (student != null){
            int index = students.indexOf(student);
            return students.remove(index);
        }
        return null;
    }

    private Student getStudent(String firstName){
        for (Student student : students){
            if (student.getFirstName().equals(firstName)){
                return student;
            }
        }
        return null;
    }
}
