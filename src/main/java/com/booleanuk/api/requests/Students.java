package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("students")
public class Students {
    private List<Student> students = new ArrayList<>(){{
        add(new Student("Nathan", "King"));
        add(new Student("Dave", "Ames"));
    }};

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> create(@RequestBody Student student) {
        if (student.getFirstName() != null && student.getLastName() != null) {
            this.students.add(student);
            return ResponseEntity.status(HttpStatus.CREATED).body(student);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping
    public List<Student> getAll() {
        return this.students;
    }

    @GetMapping("/{firstName}")
    public ResponseEntity<Student> getByFirstName(@PathVariable (name = "firstName") String firstName) {
        Optional<Student> student = students.stream().filter(x -> x.getFirstName().equalsIgnoreCase(firstName)).findFirst();
        if(student.isEmpty())
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok().body(student.get());
    }

    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> update(@PathVariable (name = "firstName") String firstName, @RequestBody Student newStudent) {
        Optional<Student> student = students.stream().filter(x -> x.getFirstName().equalsIgnoreCase(firstName)).findFirst();
        if (student.isPresent()) {
            if(newStudent.getFirstName() != null && newStudent.getLastName() != null) {
                student.get().setFirstName(newStudent.getFirstName());
                student.get().setLastName(newStudent.getLastName());
                return ResponseEntity.status(HttpStatus.CREATED).body(student.get());
            }
        }
        return ResponseEntity.badRequest().body(null);
    }

    @DeleteMapping("/{firstName}")
    public ResponseEntity<Student> delete(@PathVariable (name = "firstName") String firstName) {
        Optional<Student> student = students.stream().filter(x -> x.getFirstName().equalsIgnoreCase(firstName)).findFirst();
        if (student.isPresent()) {
            this.students.remove(student.get());
            return ResponseEntity.ok().body(student.get());
        }
        return ResponseEntity.badRequest().body(null);
    }
}
