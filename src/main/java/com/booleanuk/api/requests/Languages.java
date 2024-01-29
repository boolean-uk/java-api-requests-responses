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
    @ResponseStatus(HttpStatus.OK)
    public Language getOne(@PathVariable(name = "name") String name) {
        for (Language language : languages) {
            if(language.getName().equalsIgnoreCase(name)) {
                return language;
            }
        }
        return null;
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language update(@PathVariable(name = "name") String name, @RequestBody Language updatedLanguage) {
        for(Language language : languages) {
            if(language.getName().equalsIgnoreCase(name)) {
                language.setName(updatedLanguage.getName());

                return language;
            }
        }
        return null;
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Language delete(@PathVariable (name = "name") String name) {
        for(Language language : languages) {
            if(language.getName().equalsIgnoreCase(name)) {
                languages.remove(language);
                return language;
            }
        }
        return null;
    }
}
