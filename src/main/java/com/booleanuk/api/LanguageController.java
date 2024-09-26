package com.booleanuk.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("/language")
public class LanguageController {

    private ArrayList<Language> languages;

    public LanguageController(ArrayList<Language> languages) {
        this.languages = languages;
    }

    @GetMapping
    public ArrayList<Language> getLanguages() {
        return languages;
    }

    @GetMapping("/{name}")
    public Language getOne(@PathVariable String name) {
        for (Language lng : languages) {
            if (lng.getName().equals(name)) {
                return lng;
            }
        }
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language create(@RequestBody Language language) {
        if (!languages.contains(language) && language.getName() != null) {
            this.languages.add(language);
            return language;
        }
        return null;
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language update(@PathVariable String name, @RequestBody Language language) {
        for (Language lng : languages) {
            if (lng.getName().equals(name)) {
                lng.setName(language.getName());
                return lng;
            }
        }
        return null;
    }

    @DeleteMapping("/{name}")
    public Language delete(@PathVariable String name) {
        int id = 0;
        for (Language lng : languages) {
            if (lng.getName().equals(name)) {
                id = languages.indexOf(lng);
            }
        }
        this.languages.remove(id);
        return null;
    }
}
