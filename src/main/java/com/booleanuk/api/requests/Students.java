package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
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

	@GetMapping("/{firstName}")
	public Student getStudent(@PathVariable String firstName) {
		for (Student s : students) {
			if (s.getFirstName().equalsIgnoreCase(firstName)) {
				return s;
			}
		}
		return null;
	}

	@PutMapping("/{firstName}")
	@ResponseStatus(HttpStatus.CREATED)
	public Student updateStudent(@PathVariable String firstName, @RequestBody Student student) {
		for (Student s : students) {
			if (s.getFirstName().equalsIgnoreCase(firstName)) {
				s.setFirstName(student.getFirstName());
				s.setLastName(student.getLastName());
				return s;
			}
		}
		return null;
	}

	@DeleteMapping("/{firstName}")
	public Student deleteStudent(@PathVariable String firstName) {
		for (Student student : students) {
			if (student.getFirstName().equalsIgnoreCase(firstName)) {
				students.remove(student);
				return student;
			}
		}
		return null;
	}

}



