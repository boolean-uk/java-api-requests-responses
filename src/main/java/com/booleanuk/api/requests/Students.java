package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class Students {
    private List<Student> students;

    public Students(){
        this.students = new ArrayList<>();
        this.students.add(new Student("Nathan", "King"));
        this.students.add(new Student("Dave", "Ames"));
    }

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
    public Student getSpecificStudent(@PathVariable (name = "firstName") String firstName){

        //        Student student = null;
//        for (Student reStu : this.students){
//            if(reStu.getId() == id){
//                return reStu
//            }
//        }
//        return  null;

        for(Student s : students){
            if(s.getFirstName().equals(firstName)){
                return s;
            }
        }
        return null;
    }
    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student updateStudent(@PathVariable(name = "firstName") String firstName, @RequestBody Student student ){
        for (Student s : students){
            if(s.getFirstName().equals(firstName)){
                int index = this.students.indexOf(s);
                this.students.get(index).setFirstName(s.getFirstName());
                this.students.get(index).setLastName(s.getLastName());
                return this.students.set(index, student);
            }
        }
        return null;

    }
    @DeleteMapping("/{firstName}")
    public Student deleteStudent(@PathVariable (name = "firstName") String firstName){
        for (Student s : students){
            if (s.getFirstName().equals(firstName)){
                int index = this.students.indexOf(s);
                return this.students.remove(index);
            }
        }
        return null;
    }


}

