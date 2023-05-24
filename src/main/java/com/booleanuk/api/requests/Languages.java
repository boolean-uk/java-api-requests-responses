package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/languages")
public class Languages {
    private List<Language> languages = new ArrayList<>(){{
        add(new Language("Java"));
        add(new Language("C"));
        add(new Language("C#"));
    }};

    private Language getLanguage(String name){
        for(Language language : languages)
            if(language.getName().equals(name)) return language;

        return null;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Language> getAll(){
        return languages;
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Language get(@PathVariable(name="name") String name){
        return getLanguage(name);
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language update(@PathVariable(name = "name") String name, @RequestBody Language language){
        System.out.println(name);
        Language lang = getLanguage(name);
        if(lang != null) lang.setName(language.getName());

        return lang;
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Language delete(@PathVariable(name = "name") String name){
        Language language = getLanguage(name);
        if(language != null) languages.remove(language);

        return language;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language create(@RequestBody Language language){
       languages.add(language);
       return language;
    }

}
