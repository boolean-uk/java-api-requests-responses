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
        add(new Language("Python"));
        add(new Language("Julia"));
    }};


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language createLanguage(@RequestBody Language language) {
        this.languages.add(language);
        return language;
    }


    @GetMapping
    public List<Language> getAll() {
        return this.languages;
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Language getSpecificLanguage(@PathVariable String name) {
        List<Language> allLanguages = getAll();
        for (Language language : allLanguages) {
            if (language.getName().equalsIgnoreCase(name)) {
                return language;
            }
        }
        return null;

    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Language updateStudent(@PathVariable String name, @RequestBody Language updateLanguage) {
        List<Language> allLanguage = getAll();

        for (Language language : allLanguage) {
            if (language.getName().equalsIgnoreCase(name)) {
                language.setName(updateLanguage.getName());;
                return language;
            }
        }

        return null;
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Language deleteStudent(@PathVariable String name) {
        List<Language> allLanguage = getAll();
        allLanguage.removeIf(language -> language.getName().equalsIgnoreCase(name));

        return null;
    }

}
