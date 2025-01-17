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
    public Language getOneLanguage(@PathVariable String name) {
        Language toReturn;
        for (Language language : this.languages) {
            if (language.getName().equals(name)) {
                toReturn = language;
                return toReturn;
            }
        } return null;

    }

    @PutMapping("/{name}")
    public Language putLanguage(@PathVariable String name, @RequestBody Language language) {
        Language toPut = null;
        for (Language language1 : this.languages) {
            if (language1.getName().equals(name)) {
                language1.setName(language.getName());
                toPut = language1;
            }
        } return toPut;
    }


    @DeleteMapping("/{name}")
    public Language delete(@PathVariable String name) {
        Language toDelete;
        for (Language language : this.languages) {
            if (language.getName().equals(name)) {
                toDelete = language;
                languages.remove(language);
                return toDelete;
            }
        } return null;

    }

}
