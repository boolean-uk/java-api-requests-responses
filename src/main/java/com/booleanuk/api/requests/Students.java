package com.booleanuk.api.requests;

import com.booleanuk.api.exceptions.BadRequestException;
import com.booleanuk.api.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.StringUtils.hasLength;

@RestController
@RequestMapping("/students")
public class Students {
    private List<Student> students = new ArrayList<>(){{
        add(new Student("Nathan", "King"));
        add(new Student("Dave", "Ames"));
    }};

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@RequestBody Student student) {
        if (!hasLength(student.getFirstName()) || !hasLength(student.getLastName())) {
            throw new BadRequestException("First and last name are required.");
        }
        this.students.add(student);
        return student;
    }

    @GetMapping
    public List<Student> getAll() {
        return this.students;
    }

    @GetMapping("/{firstName}")
    public Student getSpecificStudent(@PathVariable String firstName) {
        return this.students.stream()
                .filter(student -> student.getFirstName().equalsIgnoreCase(firstName))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Student not found"));
    }

    @PutMapping("/{firstName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student update(@PathVariable String firstName, @RequestBody Student student) {
        if (!hasLength(student.getFirstName()) || !hasLength(student.getLastName())) {
            throw new BadRequestException("First and last name are required.");
        }
        Optional<Student> foundStudent = this.students.stream()
                .filter(s -> s.getFirstName().equalsIgnoreCase(firstName))
                .findFirst();
        if (foundStudent.isPresent()) {
            Student existingStudent = foundStudent.get();
            existingStudent.setFirstName(student.getFirstName());
            existingStudent.setLastName(student.getLastName());
            return existingStudent;
        } else {
            throw new NotFoundException("Student not found");
        }
    }

    @DeleteMapping("/{firstName}")
    public Student delete(@PathVariable String firstName) {
        Optional<Student> foundStudent = this.students.stream()
                .filter(s -> s.getFirstName().equalsIgnoreCase(firstName))
                .findFirst();

        if (foundStudent.isPresent()) {
            Student deletedStudent = foundStudent.get();
            this.students.remove(deletedStudent);
            return deletedStudent;
        } else {
            throw new NotFoundException("Student not found");
        }
    }
}
