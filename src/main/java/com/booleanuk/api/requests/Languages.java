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

    @GetMapping
    public List<Language> getAll(){
        return this.languages;
    }

    @GetMapping("/{name}")
    public Language getOne(@PathVariable (name = "name") String name) {
        int index = -1;
        for (Language language: languages){
            if (language.getName().equalsIgnoreCase(name)){
                index = this.languages.indexOf(language);
            }
        }
        if (index >= 0){
            return this.languages.get(index);
        }
        return null;
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language updateLanguage(@PathVariable (name = "name") String name, @RequestBody Language language){
        int index = -1;
        for (Language lang: languages){
            if (lang.getName().equalsIgnoreCase(name)){
                index = this.languages.indexOf(lang);
            }
        }
        if (index >= 0){
            this.languages.get(index).setName(language.getName());
            return this.languages.get(index);
        }
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language addLanguage(@RequestBody Language language){
        this.languages.add(language);
        return this.languages.get(this.languages.indexOf(language));
    }

    @DeleteMapping("/{name}")
    public Language deleteLanguage(@PathVariable (name = "name") String name){
        int index = -1;
        for (Language language: languages){
            if (language.getName().equalsIgnoreCase(name)){
                index = this.languages.indexOf(language);
            }
        }
        if (index >= 0){
            return this.languages.remove(index);
        }
        return null;
    }

}
