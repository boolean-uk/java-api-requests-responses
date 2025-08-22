package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/languages")
public class Languages {
    private List<Language> languages = new ArrayList<>(){{
        add(new Language("Java"));
        add(new Language("C#"));
    }};

    @PostMapping
    public ResponseEntity<Language> create(@RequestBody Language language) {
        this.languages.add(language);
        return ResponseEntity.status(HttpStatus.CREATED).body(language);
    }

    @GetMapping
    public List<Language> getAll() {
        return this.languages;
    }

    @GetMapping("/{name}")
    public ResponseEntity<Language> getLanguageByFirstName(@PathVariable String name) {
        for (Language l : languages) {
            if (l.getName().equals(name)) {
                return ResponseEntity.status(HttpStatus.OK).body(l);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{name}")
    public ResponseEntity<Language> updateLanguageName(@PathVariable String name) {
        for (Language l : languages) {
            if (l.getName().equals(name)) {
                l.setName(l.getName() + "_updated");
                return ResponseEntity.status(HttpStatus.CREATED).body(l);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteLanguage(@PathVariable String name) {
        for (Language s : languages) {
            if (s.getName().equals(name)) {
                languages.remove(s);
                return ResponseEntity.status(HttpStatus.CREATED).body("Language deleted");
            }
        }
        return ResponseEntity.notFound().build();
    }
}
