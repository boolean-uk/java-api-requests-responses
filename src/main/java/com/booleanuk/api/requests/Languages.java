package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/languages")
public class Languages {
    private List<Language> languages;

    public Languages() {
        this.languages= new ArrayList<>();

        languages.add(new Language("Java"));
        languages.add(new Language("C#"));
    }

    @PostMapping
    public ResponseEntity<Language> create(@RequestBody Language language) {
        this.languages.add(language);

        return new ResponseEntity<>(language, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Language>> getAll() {
        return new ResponseEntity<>(this.languages, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Language> getStudent(@PathVariable String name) {
        for (Language language : languages) {
            if (language.getName().equalsIgnoreCase(name)) {
                return ResponseEntity.ok(language);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{name}")
    public ResponseEntity<Language> update(@PathVariable String name, @RequestBody Language updatedLanguage) {
        for (Language language : languages) {
            if (language.getName().equalsIgnoreCase(name)) {
                language.setName(updatedLanguage.getName());
                return ResponseEntity.ok(language);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Language> delete(@PathVariable String name) {
        for(int i = 0; i < languages.size(); i++) {
            if (languages.get(i).getName().equalsIgnoreCase(name)) {
                return ResponseEntity.ok(languages.remove(i));
            }
        }
        return ResponseEntity.notFound().build();
    }
}
