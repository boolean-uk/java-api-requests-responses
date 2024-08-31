package com.booleanuk.api.requests;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentsTest {
  private Students controller;

  @BeforeEach
  public void setup() {
    this.controller = new Students();
  }

  @Test
  public void testGetAll() {
    List<Student> students = this.controller.getAll();

    Assertions.assertEquals(2, students.size());
    Assertions.assertEquals("Nathan", students.get(0).firstName());
    Assertions.assertEquals("King", students.get(0).lastName());
    Assertions.assertEquals("Dave", students.get(1).firstName());
    Assertions.assertEquals("Ames", students.get(1).lastName());
  }

  @Test
  public void testGetByFirstName() {
    Optional<Student> student = this.controller.getByFirstName("Nathan");

    Assertions.assertTrue(student.isPresent());
    Assertions.assertEquals("Nathan", student.get().firstName());
    Assertions.assertEquals("King", student.get().lastName());
  }

  @Test
  public void testUpdateByFirstName() {
    Student updatedStudent = new Student("Nigel", "Sibbert");
    Optional<Student> student = this.controller.updateByFirstName("Nathan", updatedStudent);

    Assertions.assertTrue(student.isPresent());
    Assertions.assertEquals("Nigel", student.get().firstName());
    Assertions.assertEquals("Sibbert", student.get().lastName());
  }

  @Test
  public void testDeleteByFirstName() {
    Optional<Student> student = this.controller.deleteByFirstName("Nathan");

    Assertions.assertTrue(student.isPresent());
    Assertions.assertEquals("Nathan", student.get().firstName());
    Assertions.assertEquals("King", student.get().lastName());
  }
}
