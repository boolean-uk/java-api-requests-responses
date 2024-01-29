package com.booleanuk.api.controllers;

import com.booleanuk.api.models.Language;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("languages")
public class LanguageController {
    private final List<Language> languages;

    public LanguageController() {
        this.languages = new ArrayList<>();
        this.languages.add(new Language("Java"));
        this.languages.add(new Language("C#"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language addLanguage(@RequestBody Language language) {
        this.languages.add(language);
        return language;
    }

    @GetMapping
    public List<Language> getAllLanguages() {
        return this.languages;
    }

    @GetMapping("/{name}")
    public ResponseEntity<Language> getLanguage(@PathVariable String name) {
        for (Language lang : this.languages) {
            if (lang.getName().equalsIgnoreCase(name)) {
                return ResponseEntity.ok(lang);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{name}")
    public ResponseEntity<Language> updateLanguage(@PathVariable String name, @RequestBody Language language) {
        for (Language lang : this.languages) {
            if (lang.getName().equalsIgnoreCase(name)) {
                lang.setName(language.getName());
                return ResponseEntity.ok(lang);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Language> deleteLanguage(@PathVariable String name) {
        for (int i = 0; i < this.languages.size(); i++) {
            if (this.languages.get(i).getName().equals(name)) {
                return ResponseEntity.ok(this.languages.remove(i));
            }
        }
        return ResponseEntity.notFound().build();
    }
}
