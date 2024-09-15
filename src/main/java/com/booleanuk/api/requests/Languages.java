package com.booleanuk.api.requests;

import java.util.ArrayList;
import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@RestController
@RequestMapping("languages")
public class Languages {
    private List<Language> languages = new ArrayList<>();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language create(@RequestBody Language language) {
        this.languages.add(language);

        return language;
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Language delete(@PathVariable String name) {
        Language languageToRemove = this.languages.stream()
                .filter(language -> language.getName().equals(name))
                .findFirst()
                .orElse(null);

        if (languageToRemove != null) {
            this.languages.remove(languageToRemove);
        }

        return languageToRemove;
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Language put(@PathVariable String name, @RequestBody Language updatedLanguage) {
        Iterator<Language> iterator = this.languages.iterator();

        while (iterator.hasNext()) {
            Language existingLanguage = iterator.next();
            if (existingLanguage.getName().equals(name)) {
                existingLanguage.setName(updatedLanguage.getName());

                return existingLanguage;
            }
        }

        return null;
    }

    @GetMapping
    public List<Language> getAll() {
        return this.languages;
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Language getLanguage(@PathVariable(name = "name") String name) {
        return this.languages.stream()
                .filter(language -> language.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}

