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


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language create(@RequestBody Language newLanguage){
        this.languages.add(newLanguage);
        return newLanguage;
    }

    @GetMapping
    public List<Language> getAll(){
        return this.languages;
    }

    @GetMapping("/{name}")
    public Language getOne(@PathVariable(name = "name") String name){
        for(Language newLanguage: this.languages){
            if (newLanguage.getName().equals(name)){
                return newLanguage;
            }
        }
        return null;
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language update(@PathVariable(name = "name") String name, @RequestBody Language newLanguage){
        for(Language language: this.languages){
            if (language.getName().equals(name)){
                int id = this.languages.indexOf(language);
                this.languages.set(id, newLanguage);
                return this.languages.get(id);
            }
        }
        return null;
    }

    @DeleteMapping("/{name}")
    public Language delete(@PathVariable(name = "name") String name){
        for(Language language: this.languages){
            if (language.getName().equals(name)){
                int id = this.languages.indexOf(language);
                return this.languages.remove(id);
            }
        }
        return null;
    }


}
