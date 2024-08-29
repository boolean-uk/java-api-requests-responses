package com.booleanuk.api.requests;

import org.springframework.aot.generate.ClassNameGenerator;
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

    @GetMapping
    public List<Language> getAll(){
        return this.languages;
    }

    @GetMapping("{name}")
    public Language getOne(@PathVariable String name){
        for (Language language : languages){
            if (language.getName().equals(name)){
                return language;
            }
        }
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language create(@RequestBody Language language) {
        this.languages.add(language);
        return language;
    }

    @PutMapping("{name}")
    public Language update(@PathVariable String name, @RequestBody Language language){
        for(int i = 0; i < languages.size(); i++){
            if (languages.get(i).getName().equals(name)){
                languages.set(i, language);
                return languages.get(i);
            }
        }
        return null;
    }

    @DeleteMapping("{name}")
    public Language delete(@PathVariable String name){
        for (Language language : languages){
            if(language.getName().equals(name)){
                languages.remove(language);
                return language;
            }
        }
        return null;
    }

}
