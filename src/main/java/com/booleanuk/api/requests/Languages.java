package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("languages")
public class Languages {
    private List<Language> languages;

    public Languages() {
        this.languages = new ArrayList<>(){{
            add(new Language("Java"));
            add(new Language("C#"));
        }};
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Language> create(@RequestBody Language language) {
        this.languages.add(language);

        return ResponseEntity.ok(language);
    }

    @GetMapping
    public List<Language> getAll() {
        return this.languages;
    }
}
