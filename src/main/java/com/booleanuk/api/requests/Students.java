package com.booleanuk.api.requests;

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

    @GetMapping("/{firstname}")
    public Student getSpecificStudent(@PathVariable String firstname){
        for (Student student: this.students){
            if (student.getFirstName().equals(firstname)){
                return student;
            }
        }
        return null;
    }

    @PutMapping("/{firstname}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student updateSpecificStudent(@PathVariable String firstname, @RequestBody Student student) {
        for (Student anotherStudent: this.students){
            if (anotherStudent.getFirstName().equals(firstname)){
                anotherStudent.setFirstName(student.getFirstName());
                anotherStudent.setLastName(student.getLastName());
                return anotherStudent;
            }
        }
        return null;
    }

    @DeleteMapping("/{firstname}")
    public Student deleteSpecificStudent(@PathVariable String firstname){
        int index= -1;
        for (int i=0; i<this.students.size(); i++){
            if (this.students.get(i).getFirstName().equals(firstname)){
                index = i;
                break;
            }
        }
        if (index == -1){
            return null;
        } else {
            return this.students.remove(index);
        }
    }
}
