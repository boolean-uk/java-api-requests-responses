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
    public List<Language> getAll()
    {
        return this.languages;
    }

    @GetMapping("/{name}")
    public Language getSpecificLanguage(@PathVariable(name = "name") String name)
    {
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
        this.languages.add(language);
        return this.languages.get(this.languages.indexOf(language));
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language updateLanguage(@PathVariable(name = "name") String name, @RequestBody Language language) {
        for (Language lang : languages) {
            if (lang.getName().equals(name)) {
                lang.setName(language.getName());
                return lang;
            }
        }
        return null;
    }

    @DeleteMapping("/{name}")
    public Language delete(@PathVariable String name) {
        int id = 0;
        for (Language lang : languages) {
            if (lang.getName().equals(name)) {
                id = languages.indexOf(lang);
            }
        }
        this.languages.remove(id);
        return null;
    }
}
