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

    @GetMapping
    public List<Language> getAll() {
        return this.languages;
    }

    @GetMapping("/{name}")
    public Language getSpecificLanguage(@PathVariable(name = "name") String name) {
        for (Language language : languages) {
            if (language.getName().equals(name)) {
                return language;
            }
        }
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language createLanguage(@RequestBody Language language) {
        Language newLanguage = new Language(language.getName());
        this.languages.add(newLanguage);
        return newLanguage;
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language updateLanguage(@PathVariable(name = "name") String name, @RequestBody Language updatedLanguage) {
        for (Language language : languages) {
            if (language.getName().equals(name)) {
                language = new Language(updatedLanguage.getName());
                return language;
            }
        }
        return null;
    }

    @DeleteMapping("/{name}")
    public Language delete(@PathVariable String name) {
        for (Language language : languages) {
            if (language.getName().equals(name)) {
                this.languages.remove(language);
                return language;
            }
        }
        return null;
    }
}
