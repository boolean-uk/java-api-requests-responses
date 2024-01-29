package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("languages")
public class Languages {
    private List<Language> languages;
    public Languages() {
        this.languages = new ArrayList<>();

        this.languages.add(new Language("Java"));
        this.languages.add(new Language("C#"));

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language create(@RequestBody Language language) {
        this.languages.add(language);
        return language;
    }

    @GetMapping
    public List<Language> getAllLanguages() {
        return this.languages;
    }

    @GetMapping("{id}")
    public Language getOneLanguage(@PathVariable int id) {
        if(id < this.languages.size()) {
            return this.languages.get(id);
        }
        return null;
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language update(@PathVariable int id, @RequestBody Language language) {
        if(id < this.languages.size()) {
            this.languages.get(id).setName(language.getName());
            return this.languages.get(id);
        }
        return null;
    }

    @DeleteMapping("{id}")
    public Language delete(@PathVariable int id) {
        if(id < this.languages.size()) {
            return this.languages.remove(id);
        }
        return null;
    }
}
