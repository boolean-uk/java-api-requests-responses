package com.booleanuk.api.requests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        System.out.println("Checking for " + name);
        for (Student student : students) {
            String studName = student.getFirstName();
            boolean sameName = studName.equals(name);
            if (sameName) {
                return student;
            }
        }

        return null;
    }

    @PutMapping("{firstName}")
    public Student putStudent(@PathVariable String firstName, @RequestBody Student student){
        for(int i = 0; i < students.size(); i++){
            Student s = students.get(i);
            if (s.getFirstName().equals(firstName)){
                students.remove(i);
                students.add(i, student);
            }
        }
        return student;
    }

    @DeleteMapping("{firstName}")
    public Student deleteStudent(@PathVariable String firstName){
        for(int i = 0; i < students.size(); i++){
            Student s = students.get(i);
            if (s.getFirstName().equals(firstName)){
                return students.remove(i);
            }
        }

        return null;

    }

}
