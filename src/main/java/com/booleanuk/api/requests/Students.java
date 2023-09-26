package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class Students {
    private List<Student> students = new ArrayList<>() {{
        add(new Student("Nathan", "King"));
        add(new Student("Dave", "Ames"));
    }};


    // made them private, because of: Class 'Student' is exposed outside its defined visibility scope
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Student create(@RequestBody Student student) {
        this.students.add(student);

        return student;
    }

    @GetMapping
    private List<Student> getAll() {
        return this.students;
    }

    @GetMapping("/{firstName}")
    private Student getSpecificStudent(@PathVariable String firstName) {
        for (Student student : this.students) {
            if (student.getFirstName().equalsIgnoreCase(firstName)) {
                return student;
            }
        }
        return null;
    }

    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    private Student updateStudent(@PathVariable String firstName, @RequestBody Student student) {
        for (Student existingStudent : this.students) {
            if (existingStudent.getFirstName().equalsIgnoreCase(firstName)) {
                existingStudent.setFirstName(student.getFirstName());
                existingStudent.setLastName(student.getLastName());
                return existingStudent;
            }
        }
        return null;
    }

    @DeleteMapping("/{firstName}")
    private Student deleteStudent(@PathVariable String firstName) {
        for (Student existingStudent : this.students) {
            if (existingStudent.getFirstName().equalsIgnoreCase(firstName)) {
//                Student deletedStudent =  existingStudent;
                this.students.remove(existingStudent);
                return existingStudent;
            }
        }
        return null;
    }
}

