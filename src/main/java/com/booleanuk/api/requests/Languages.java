package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Language getLanguage(@PathVariable String name){
        for (Language language1: this.languages){
            if (language1.getName().equals(name)){
                return language1;
            }
        }
        return null;
    }
    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language updateLanguage(@PathVariable String name, @RequestBody Language language) {
        for (Language language1 : this.languages) {
            if (language1.getName().equals(name)){
                language1.setName(language.getName());
                return language1;
            }
        }
        return null;
    }
    @DeleteMapping("/{name}")
    public List<Language> deleteStudent(@PathVariable String name){
        this.languages.remove(name);
        return this.languages;
    }

}
