package com.booleanuk.api.service;

import com.booleanuk.api.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

        private List<Student> students = new ArrayList<>() {{
            add(new Student("Nathan", "King"));
            add(new Student("Dave", "Ames"));
        }};
    public Student getSpecificStudent(String firstName) {
        Student foundStudent = students.stream()
                .filter(student -> student.getFirstName().equalsIgnoreCase(firstName))
                .findFirst()
                .orElse(null);
        return foundStudent;
    }
    public Student updateStudent(String firstName, Student student) {

        Student foundStudent = students.stream()
                .filter(s -> s.getFirstName().equalsIgnoreCase(firstName))
                .findFirst()
                .orElse(null);
        if (foundStudent != null) {
            foundStudent.setFirstName(student.getFirstName());
            foundStudent.setLastName(student.getLastName());
        }
        return foundStudent;
    }
    public Student deleteStudent(String firstName) {
        Student foundStudent = students.stream()
                .filter(s -> s.getFirstName().equalsIgnoreCase(firstName))
                .findFirst()
                .orElse(null);
        if (foundStudent != null) {
            students.remove(foundStudent);
        }
        return foundStudent;
    }

    public List<Student> getStudents() {
        return students;
    }
}
