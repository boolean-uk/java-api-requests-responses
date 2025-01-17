package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("languages")
public class Languages {
    private List<Language> languages = new ArrayList<>(){{
        add(new Language("Java"));
        add(new Language("C#"));
    }};

    // Creating language and adding it to the list
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language create(@RequestBody Language language) {
        this.languages.add(language);

        return language;
    }

    // Get all languages
    @GetMapping
    public List<Language> getAll(){
        return this.languages;
    }

    // Get language by name
    @GetMapping("/{name}")
    public Language getSpecificLanguage(@PathVariable String name){
        for(Language aLanguage : this.languages){
            if(aLanguage.getName().equals(name)){
                return aLanguage;
            }
        }
        return null;
    }

    // Update language by name
    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language updateSpecificLanguage(@PathVariable String name, @RequestBody Language language){
        for(Language aLanguage : this.languages){
            if(aLanguage.getName().equals(name)){
                aLanguage.setName(language.getName());
                return aLanguage;
            }
        }
        return null;
    }

    // Deleting language by name
    @DeleteMapping("/{name}")
    public Language deleteSpecificLanguage(@PathVariable String name){
        for(Language aLanguage : this.languages){
            if(aLanguage.getName().equals(name)){
                this.languages.remove(aLanguage);
                return aLanguage;
            }
        }
        return null;
    }
}
