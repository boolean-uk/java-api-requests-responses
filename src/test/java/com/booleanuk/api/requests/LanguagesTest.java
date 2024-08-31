package com.booleanuk.api.requests;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LanguagesTest {
  private Languages controller;

  @BeforeEach
  public void setup() {
    this.controller = new Languages();
  }

  @Test
  public void testGetAll() {
    List<Language> languages = this.controller.getAll();

    Assertions.assertEquals(2, languages.size());
    Assertions.assertEquals("Java", languages.get(0).name());
    Assertions.assertEquals("C#", languages.get(1).name());
  }

  @Test
  public void testGetByName() {
    Optional<Language> language = this.controller.getByName("Java");

    Assertions.assertTrue(language.isPresent());
    Assertions.assertEquals("Java", language.get().name());
  }

  @Test
  public void testUpdateByName() {
    Language updatedLanguage = new Language("C");
    Optional<Language> language = this.controller.updateByName("C#", updatedLanguage);

    Assertions.assertTrue(language.isPresent());
    Assertions.assertEquals("C", language.get().name());
  }

  @Test
  public void testDeleteByName() {
    Optional<Language> language = this.controller.deleteByName("C#");

    Assertions.assertTrue(language.isPresent());
    Assertions.assertEquals("C#", language.get().name());
  }
}
