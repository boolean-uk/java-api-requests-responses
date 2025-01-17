package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("languages")
public class LanguageController {
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
    public Language getOne(@PathVariable String name) {
        for (int i = 0; i < languages.size(); i++) {
            if (languages.get(i).getName().equals(name)) {
                return languages.get(i);
            }
        }
        return null;
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language update(@PathVariable String name, @RequestBody Language language) {
        for (int i = 0; i < languages.size(); i++) {
            if (languages.get(i).getName().equals(name)) {
                languages.get(i).setName(language.getName());
                return language;
            }
        }
        return null;
    }

    @DeleteMapping("/{name}")
    public Language delete(@PathVariable String name) {
        Language deletedLanguage;
        for (int i = 0; i < languages.size(); i++) {
            if (languages.get(i).getName().equals(name)) {
                deletedLanguage = languages.get(i);
                languages.remove(languages.get(i));
                return deletedLanguage;
            }
        }
        return null;
    }
}
