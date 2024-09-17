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
    public Language createLanguage(@RequestBody Language newLanguage) {
        languages.add(newLanguage);
        return newLanguage;
    }

    @GetMapping
    public List<Language> getAllLanguages() {
        return languages;
    }

    @GetMapping("/{name}")
    public Language getSpecificLanguage(@PathVariable String name) {
        return languages.stream()
                .filter(language -> language.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @PutMapping("/{name}")
    public Language updateLanguage(@PathVariable String name, @RequestBody Language updatedLanguage) {
        Language languageToUpdate = languages.stream()
                .filter(language -> language.getName().equals(name))
                .findFirst()
                .orElse(null);

        if (languageToUpdate != null) {
            languageToUpdate.setName(updatedLanguage.getName());
        }

        return languageToUpdate;
    }

    @DeleteMapping("/{name}")
    public Language deleteLanguage(@PathVariable String name) {
        Language languageToDelete = languages.stream()
                .filter(language -> language.getName().equals(name))
                .findFirst()
                .orElse(null);

        if (languageToDelete != null) {
            languages.remove(languageToDelete);
        }

        return languageToDelete;
    }
}

