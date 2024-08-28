package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/languages")
public class Languages {
    private final List<Language> languageList;

    public Languages(){
        this.languageList = new ArrayList<>();
        this.languageList.add(new Language("Java"));
        this.languageList.add(new Language("C#"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language add(@RequestBody Language language){
        this.languageList.add(language);
        return language;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List <Language> getAll(){
        return this.languageList;
    }

    @GetMapping("/{languageName}")
    @ResponseStatus(HttpStatus.OK)
    public Language get(@PathVariable String languageName){
        return getLanguage(languageName);
    }

    // Helper function to adhere to DRY and increase readability
    private Language getLanguage(String languageName){
        return this.languageList.stream()
                .filter(l -> l.getName().equalsIgnoreCase(languageName))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Language not found"));
    }

    @PutMapping("/{languageName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language update(@PathVariable String languageName, @RequestBody Language language){
        Language existingLanguage = getLanguage(languageName);
        existingLanguage.setName(language.getName());
        return existingLanguage;
    }

    @DeleteMapping("/{languageName}")
    @ResponseStatus(HttpStatus.OK)
    public Language delete(@PathVariable String languageName){
        Language language = getLanguage(languageName);
        this.languageList.remove(language);
        return language;
    }
}
