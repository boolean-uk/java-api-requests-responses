package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/languages")
public class Languages {


    private List<Language> languages = new ArrayList<>(){{
        add(new Language("Java"));
        add(new Language("C#"));
    }};

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language createLanguage(@RequestBody Language language){
        languages.add(language);
        return language;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Language> getAllLanguages(){
        return languages;
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Language getLanguage(@RequestBody Language language, @PathVariable String name){

        for (Language l: languages){
            if (Objects.equals(l.getName(), name)){
                return l;
            }

        }

        return null;

    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language updateLanguage(@RequestBody Language language, @PathVariable String name){

        for (Language l: languages){
            if (Objects.equals(l.getName(), name)){
                l.setName(language.getName());
                return l;
            }
        }


        return null;

    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Language deleteLanguage(@RequestBody Language language, @PathVariable String name){

        for (Language l: languages){
            if (Objects.equals(l.getName(), name)){
                languages.remove(l);
                return l;
            }
        }


        return null;

    }





}
