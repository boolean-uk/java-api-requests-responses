package com.booleanuk.api.requests;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("languages")
public class Languages {

    private List<Language> languages = new ArrayList<>() {{
        add(new Language("Java"));
        add(new Language("C#"));
    }};

    @PostMapping
    public ResponseEntity<?> createLanguage(@RequestBody Language language) {
        for (Language lang : languages) {
            if (lang.getName().equalsIgnoreCase(language.getName())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Language already exists.");
            }
        }
        languages.add(language);
        return ResponseEntity.status(HttpStatus.CREATED).body(language);
    }

    @GetMapping
    public ResponseEntity<?> getAllLanguages() {
        return ResponseEntity.ok(languages);
    }

    // NOTE: 'C#' DOES NOT WORK as path variable, use '%23' for postman

    @GetMapping("/{name}")
    public ResponseEntity<?> getALanguage(@PathVariable String name) {
        for (Language lang : languages) {
            if (lang.getName().equalsIgnoreCase(name)) {
                return ResponseEntity.ok(lang);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Language not found.");
    }

    @PutMapping("/{name}")
    public ResponseEntity<?> updateLanguage(@PathVariable String name) {
        for (Language lang : languages) {
            if (lang.getName().equalsIgnoreCase(name)) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Updated Language");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Language not found.");
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteLanguage(@PathVariable String name) {
        boolean removed = languages.removeIf(
                language -> language.getName().equalsIgnoreCase(name));

        if (removed) {
            return ResponseEntity.ok("Language deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Language not found.");
        }
    }
}
