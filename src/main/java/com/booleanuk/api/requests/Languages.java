package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("languages")
public class Languages {
    private ArrayList<Language> languages;

    public Languages() {
        this.languages = new ArrayList<>();
    }

    @GetMapping
    public ArrayList<Language> getAllLanguages() {
        return this.languages;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language createLanguage(@RequestBody Language language) {
        this.languages.add(language);
        return language;
    }

    @GetMapping("/{language}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language getSpecificLanguage(@PathVariable String language) {
        for (Language l: languages) {
            if (l.getName().equals(language)) {
                return l;
            }
        }
        return null;
    }


    @PutMapping("/{language}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language updatLanguage(@PathVariable String language, @RequestBody Language newLanguage) {
        for (Language l: languages) {
            if (l.getName().equals(language)) {
                l.setName(newLanguage.getName());
                return l;
            }
        }
        return null;
    }


    @DeleteMapping("/{language}")
    public ArrayList<Language> deleteLanguage(@PathVariable String language) {
        for (Language l: languages) {
            if (l.getName().equals(language)) {
                languages.remove(l);
            }
        }
        return languages;
    }
}
