package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

//Core Exercise Part 2
@RestController
@RequestMapping("/languages")
public class LanguageController {
    private HashMap<String, Language> languages;

    public LanguageController() {
        this.languages = new HashMap<>();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public HashMap<String, Language> getAllLanguages() {
        return this.languages;
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Language getLanguageByName(@PathVariable(name = "name") String name) {
        return this.languages.get(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language createLanguage(@RequestBody Language language) {
        if(!this.languages.containsKey(language.getName())) {
            this.languages.put(language.getName(), language);
            return language;
        }
        return null;
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language updateLanguage(@PathVariable(name = "name") String name, @RequestBody Language language) {
        if (this.languages.containsKey(name)) {
            //language.setName(name);
            this.languages.put(name, language);
            return language;
        }
        return null;
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Language deleteLanguage(@PathVariable(name = "name") String name) {
        return this.languages.remove(name);
    }
}

