package com.booleanuk.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("students")
public class StudentController {
    private ArrayList<Student> students;

    public StudentController(){
        this.students = new ArrayList<>();
        students.add(new Student("Hassan1", "Shitt"));
        students.add(new Student("Hassan2", "Shitt"));
        students.add(new Student("Hassan3", "Shitt"));
    }
    @GetMapping
    public ArrayList<Student> getAll(){
        return this.students;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student created(@RequestBody Student author){
        this.students.add(author);
        return author;
    }

    @GetMapping("students/{id}")
    public String getOneStudent(@PathVariable int id){
        if ( id < this.students.size()){
            return this.students.get(id).firstName;
        }
        return null;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student update(@PathVariable int id, @RequestBody Student author){
        if (id < this.students.size()){
            this.students.get(id).setFirstName(author.getFirstName());
            this.students.get(id).setLastName(author.getLastName());
            return this.students.get(id);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Student delete(@PathVariable int id){
        if (id < this.students.size()){
            return this.students.remove(id);
        }
        return null;
    }

}
