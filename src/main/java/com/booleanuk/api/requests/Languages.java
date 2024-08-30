package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("languages")
public class Languages {
    private List<Language> languages = new ArrayList<>(){{
        add(new Language("Java"));
        add(new Language("C"));
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
    @ResponseStatus(HttpStatus.OK)
    public Language getLanguage(@PathVariable String name){

        return this.languages.stream()
                .filter(language -> language.getName().equalsIgnoreCase(name))
                .findAny()
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Language not found!"));

    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Language update(@PathVariable String name, @RequestBody Language updatedLanguage){

        Language language = update(name);

        language.setName(updatedLanguage.getName());


        return language;

    }


    public Language update(String name){

        return languages.stream()
                .filter(s -> s.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Language not found!"));
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Language deleteLanguage(@PathVariable String name){

        Language language = delete(name);
        this.languages.remove(language);

        return language;
    }


    public Language delete(String name){
        return this.languages.stream()
                .filter(l -> l.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Language not found!"));
    }
}



