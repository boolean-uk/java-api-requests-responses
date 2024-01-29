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
    public String getLanguageByName(@PathVariable String name) {
        for (Language l : languages) {
            if (l.getName().equals(name)) {
                return l.getName();
            }
        }
        return "This language doesn't exist";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language create(@RequestBody Language language) {
        this.languages.add(language);
        return language;
    }

    @PutMapping("/{languageName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language updateLanguage(@PathVariable String languageName, @RequestBody Language updatedLanguage) {
        for (Language l : this.languages) {
            if (l.getName().equals(languageName)) {
                l.setName(updatedLanguage.getName());
                return l;
            }
        }
        return null;
    }

    @DeleteMapping("/{language}")
    public Language deleteLanguage(@PathVariable String language) {
        for (Language l : this.languages) {
            if (l.getName().equals(language)) {
                languages.remove(l);
                return l;
            }
        }
        return null;
    }
}