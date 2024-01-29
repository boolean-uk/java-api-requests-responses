package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("languages")
public class Languages {
    private List<Language> languages;

    public Languages(){
        this.languages = new ArrayList<>();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language create(@RequestBody Language language) {
        System.out.println("Post");
        this.languages.add(language);
        return language;
    }

    @GetMapping
    public  List<Language> getAll(){
        System.out.println("Get");
        return this.languages;
    }

    @GetMapping("/{name}")
    public Language getOne(@PathVariable String name){
        for (Language language : languages){
            if(language.getName().equalsIgnoreCase(name)){
                return language;
            }
        }
        return null;
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language update(@PathVariable String name, @RequestBody Language language){
        for (Language languageToChange : this.languages){
            System.out.println(languageToChange.getName());
            if(languageToChange.getName().equalsIgnoreCase(name)){
                languageToChange.setName(language.getName());
                return languageToChange;
            }
        }
        return null;
    }

    @DeleteMapping("/{name}")
    public Language delete(@PathVariable String name) {
        for (Language language : languages){
            if(language.getName().equalsIgnoreCase(name)){
                languages.remove(language);
                return language;
            }
        }
        return null;
    }
}
