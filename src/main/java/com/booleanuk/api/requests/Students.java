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
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getAll() {
        return this.students;
    }

    @GetMapping("/{firstName}")
    @ResponseStatus(HttpStatus.OK)
    public Student getStudentByFirstName(@PathVariable("firstName") String firstName) {
        return this.students.stream()
                .filter(student -> student.getFirstName().equalsIgnoreCase(firstName))
                .findFirst()
                .orElse(null);
    }
    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student putStudentByFirstName(@PathVariable("firstName") String firstName,  @RequestBody Student updatedStudent ) {
       Student student = this.students.stream()
                .filter(loopstudent -> loopstudent.getFirstName().equalsIgnoreCase(firstName))
                .findFirst()
                .orElse(null);
       if(student != null){
           student.setFirstName(updatedStudent.getFirstName());
           student.setLastName(updatedStudent.getLastName());
           return student;
       }else{
           return null;
       }
    }
    @DeleteMapping("/{firstName}")
    @ResponseStatus(HttpStatus.OK)
    public Student deleteStudentByFirstName(@PathVariable("firstName") String firstName) {
        for (int i = 0; i < this.students.size(); i++) {
            if(this.students.get(i).getFirstName().equalsIgnoreCase(firstName)){
                Student deletedStudent = this.students.get(i);
                this.students.remove(i);
                return deletedStudent;
            }
        }
        return null;
    }
}
