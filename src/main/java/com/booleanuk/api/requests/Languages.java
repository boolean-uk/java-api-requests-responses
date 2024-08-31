package com.booleanuk.api.requests;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/languages")
public class Languages {
  private final List<Language> languages = new ArrayList<>() {
    {
      add(new Language("Java"));
      add(new Language("C#"));
    }
  };

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Language> getAll() {
    return this.languages;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Language create(@RequestBody Language language) {
    this.languages.add(language);

    return language;
  }

  @GetMapping("/{name}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<Language> getByName(@PathVariable String name) {
    return this.languages
        .stream()
        .filter(language -> language.name().equals(name))
        .findFirst();
  }

  @PutMapping("/{name}")
  @ResponseStatus(HttpStatus.CREATED)
  public Optional<Language> updateByName(@PathVariable String name, @RequestBody Language newLanguage) {
    return this.getByName(name)
        .map(oldLanguage -> {
          this.languages.remove(oldLanguage);
          this.languages.add(newLanguage);
          return newLanguage;
        });
  }

  @DeleteMapping("/{name}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<Language> deleteByName(@PathVariable String name) {
    return this.getByName(name)
        .map(languageToDelete -> {
          this.languages.remove(languageToDelete);
          return languageToDelete;
        });
  }
}
