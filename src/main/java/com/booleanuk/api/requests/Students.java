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

    @GetMapping("/{studentName}")
    public Student getOne(@PathVariable(name = "studentName") String studentName) {
        for(Student student : students){
            if (student.getFirstName().equals(studentName)){
                return student;
            }
        }
        return null;
    }

    @PutMapping("/{studentName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student update(@PathVariable(name = "studentName") String studentName,@RequestBody Student student) {
        for(Student student1 : students){
            if (student1.getFirstName().equals(studentName)){
                student1.setFirstName(student.getFirstName());
                student1.setLastName(student.getLastName());
                return student1;
            }
        }
        return null;
    }

    @DeleteMapping("/{studentName}")
    public Student delete(@PathVariable (name = "studentName") String studentName) {
        for(Student student1 : students){
            if (student1.getFirstName().equals(studentName)){
                students.remove(student1);
                return student1;
            }
        }
        return null;
    }


}
