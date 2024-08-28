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
    @ResponseStatus(HttpStatus.OK)
    public Language getSpecificLanguage(@PathVariable String name) {
        return findLanguage(name);
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language updateLanguage(@PathVariable String name, @RequestBody Language updatedLanguage) {
        for(Language language : this.languages) {
            if(language.getName().equalsIgnoreCase(name)) {
                language.setName(updatedLanguage.getName());
                return language;
            }
        }

        return null;
    }



    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Language deleteLanguage(@PathVariable String name) {
        Language languageToDelete = findLanguage(name);

        if(languageToDelete != null) {
            this.languages.remove(languageToDelete);
        }

        return languageToDelete;
    }

    private Language findLanguage(String name) {
        return this.languages.stream()
                .filter(language -> language.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
