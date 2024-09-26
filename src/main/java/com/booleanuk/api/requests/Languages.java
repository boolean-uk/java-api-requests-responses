package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/languages")
public class Languages {
    private List<Language> languages = new ArrayList<>(){{
        add(new Language("Java"));
        add(new Language("C-Sharp"));
    }};

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Language> create(@RequestBody Language language) {
        if (language.getName() != null) {
            this.languages.add(language);
            return ResponseEntity.status(HttpStatus.CREATED).body(language);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping
    public List<Language> getAll() {
        return this.languages;
    }

    @GetMapping("/{name}")
    public ResponseEntity<Language> getByName(@PathVariable (name = "name") String name) {
        Optional<Language> language = languages.stream().filter(x -> x.getName().equalsIgnoreCase(name)).findFirst();
        if(language.isEmpty())
            return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok().body(language.get());
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Language> update(@PathVariable (name = "name") String name, @RequestBody Language newLanguage) {
        Optional<Language> language = languages.stream().filter(x -> x.getName().equalsIgnoreCase(name)).findFirst();
        if (language.isPresent()) {
            if(newLanguage.getName() != null) {
                language.get().setName(newLanguage.getName());
                return ResponseEntity.status(HttpStatus.CREATED).body(language.get());
            }
        }
        return ResponseEntity.badRequest().body(null);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Language> delete(@PathVariable (name = "name") String name) {
        Optional<Language> language = languages.stream().filter(x -> x.getName().equalsIgnoreCase(name)).findFirst();
        if (language.isPresent()) {
            this.languages.remove(language.get());
            return ResponseEntity.ok().body(language.get());
        }
        return ResponseEntity.badRequest().body(null);
    }


}
