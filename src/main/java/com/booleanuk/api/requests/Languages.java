package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/languages")
public class Languages {
    private final List<Language> languages = new ArrayList<>(){{
        add(new Language("Java"));
        add(new Language("C#"));
    }};

    @GetMapping
    public List<Language> getLanguages(){
        return this.languages;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language postLanguage(@RequestBody Language language){
        this.languages.add(language);
        return language;
    }

    @GetMapping("/{name}")
    public Language getLanguage(@PathVariable String name){
        for (Language language: this.languages){
            if (language.getName().equals(name)){
                return language;
            }
        }
        return null;
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language putLanguage(@PathVariable String name, @RequestBody Language language){
        for (Language anotherLanguage: this.languages){
            if (anotherLanguage.getName().equals(name)){
                anotherLanguage.setName(language.getName());
                return anotherLanguage;
            }
        }
        return null;
    }

    @DeleteMapping("/{name}")
    public Language deleteLanguage(@PathVariable String name){
        int index = -1;
        for (int i=0; i<this.languages.size(); i++){
            if (this.languages.get(i).getName().equals(name)){
                index = i;
                break;
            }
        }
        if (index == -1){
            return null;
        } else {
            return this.languages.remove(index);
        }
    }
}
