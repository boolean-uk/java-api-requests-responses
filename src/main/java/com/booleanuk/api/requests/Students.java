package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class Students {
  private final List<Student> students = new ArrayList<>() {
    {
      this.add(new Student("Nathan", "King"));
      this.add(new Student("Dave", "Ames"));
    }
  };

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

  @GetMapping(value = "/{firstName}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<Student> getByFirstName(@PathVariable String firstName) {
    return this.students
        .stream()
        .filter(student -> student.firstName().equals(firstName))
        .findFirst();
  }

  @PutMapping(value = "/{firstName}")
  @ResponseStatus(HttpStatus.CREATED)
  public Optional<Student> updateByFirstName(@PathVariable String firstName, @RequestBody Student newStudent) {
    return this.getByFirstName(firstName)
        .map(oldStudent -> {
          this.students.remove(oldStudent);
          this.students.add(newStudent);
          return newStudent;
        });
  }

  @DeleteMapping(value = "/{firstName}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<Student> deleteByFirstName(@PathVariable String firstName) {
    return this.getByFirstName(firstName)
        .map(studentToRemove -> {
          this.students.remove(studentToRemove);
          return studentToRemove;
        });
  }
}
