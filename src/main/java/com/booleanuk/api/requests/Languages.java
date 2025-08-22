package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/languages")
public class Languages {
    private List<Language> languages = new ArrayList<>(){{
        add(new Language("Java"));
        add(new Language("C#"));
    }};
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language createLanguage(@RequestBody Language language) {
        this.languages.add(language);
        return language;
    }
    @GetMapping
    public List<Language> getLanguages() {
        return this.languages;
    }
    @GetMapping("/{name}")
    public Language getSpecificLanguage(@PathVariable String name) {
        Language foundLanguage = languages.stream().filter(l -> l.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
        return foundLanguage;
    }
    @PutMapping("/{name}")
    public Language updateLanguage(@PathVariable String name, @RequestBody Language language) {
        Language foundLanguage = languages.stream().filter(l -> l.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
        if (foundLanguage != null) {
            foundLanguage.setName(language.getName());

        }
        return foundLanguage;
    }
    @DeleteMapping("/{name}")
    public Language deleteLanguage(@PathVariable String name) {
        Language foundLanguage = languages.stream().filter(language -> language.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
        if (foundLanguage != null) {
            languages.remove(foundLanguage);
        }
        return foundLanguage;
    }
}
