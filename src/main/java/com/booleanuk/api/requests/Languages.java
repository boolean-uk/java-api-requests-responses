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
    public Language create(@RequestBody Language language) {
        this.languages.add(language);

        return language;
    }


    @GetMapping
    public List<Language> getAll() {
        return this.languages;
    }

    @GetMapping("/{name}")
    public Language getlanguage(@PathVariable String name){
        for(Language language : languages){
            if(language.getName().equals(name)){
                return language;
            }
        }
        return null;
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language updatelanguage(@PathVariable String name, @RequestBody Language language){
        for(Language l : languages){
            if(l.getName().equals(name)){
                l.setName(language.getName());
                return l;
            }
        }

        return null;
    }

    @DeleteMapping("/{name}")
    public Language deleteLanguage(@PathVariable String name){
        for(Language language : languages){
            if(language.getName().equals(name)){
                this.languages.remove(language);
                return language;
            }
        }
        return null;
    }
}
