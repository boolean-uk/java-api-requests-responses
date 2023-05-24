package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@RestController
@RequestMapping("languages")
public class Languages {
    private List<Language> languages = new ArrayList<>(){{
        add(new Language("Java"));
        add(new Language("C#"));
    }};

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language create(@RequestBody Language language) {
        this.languages.add(language);
        return language;
    }

    @GetMapping
    public List<Language> getAll() {
        return this.languages;
    }

    @GetMapping("/{name}")
    public ResponseEntity<Language> getOne(@PathVariable String name){
        for (Language language : languages) {
            if (Objects.equals(language.getName(),name)) {
                return new ResponseEntity<>(language,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{name}")
    public ResponseEntity<Language> deleteLanguage(@PathVariable String name){
        for (int i = 0; i < languages.size(); i++) {
            Language language = languages.get(i);
            if (Objects.equals(language.getName(), name)) {
                return new ResponseEntity<>(languages.remove(i),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{name}")
    public ResponseEntity<Language> updateLanguage(@PathVariable String name,@RequestBody Language updatedLanguage) {
        for (int i = 0; i < languages.size(); i++) {
            Language language = languages.get(i);
            if (Objects.equals(language.getName(), name)) {
                languages.get(i).setName(updatedLanguage.getName());
                return new ResponseEntity<>(languages.get(i),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
}
