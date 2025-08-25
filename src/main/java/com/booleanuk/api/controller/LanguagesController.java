package com.booleanuk.api.controller;

import com.booleanuk.api.model.Language;
import com.booleanuk.api.service.LanguageService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/languages")
public class LanguagesController {

    private LanguageService languageService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language createLanguage(@RequestBody Language language) {
       languageService.getLanguages().add(language);
        return language;
    }
    @GetMapping
    public List<Language> getLanguages() {
        return languageService.getLanguages();
    }
    @GetMapping("/{name}")
    public Language getSpecificLanguage(@PathVariable String name) {
        Language foundLanguage = languageService.getSpecificLanguage(name);
        return foundLanguage;
    }
    @PutMapping("/{name}")
    public Language updateLanguage(@PathVariable String name, @RequestBody Language language) {
        Language foundLanguage = languageService.updateLanguage(name, language);
        return foundLanguage;
    }
    @DeleteMapping("/{name}")
    public Language deleteLanguage(@PathVariable String name) {
        Language foundLanguage = languageService.deleteLanguage(name);
        return foundLanguage;
    }
}
