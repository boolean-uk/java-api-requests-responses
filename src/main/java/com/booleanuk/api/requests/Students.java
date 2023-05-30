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
    public Student getOne(@PathVariable( name = "firstName") String firstName) {
        //Student newStudent;
        for(Student newStudent : this.students){
            if (newStudent.getFirstName().equals(firstName)){
                return newStudent;
            }

        }
        return null;
    }

    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student update(@PathVariable( name = "firstName") String firstName, @RequestBody Student student){
        for(Student newStudent : this.students){
            if (newStudent.getFirstName().equals(firstName)){
                int id = this.students.indexOf(newStudent);
                this.students.set(id, newStudent);
                return this.students.get(id);
            }

        }
        return null;
    }

    @DeleteMapping("/{firstName}")
    public Student delete(@PathVariable( name = "firstName") String firstName){
        for(Student newStudent : this.students){
            if (newStudent.getFirstName().equals(firstName)){
                int id = this.students.indexOf(newStudent);
                return this.students.remove(id);
            }

        }
        return null;
    }


}
