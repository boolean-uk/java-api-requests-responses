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
    public Language getOne(@PathVariable( name = "name" )String name) {
        for (Language language: languages) {
            if (name.equals(language.getName())){
                return language;
            }
        }
        return null;
    }
    @PutMapping("/name")
    @ResponseStatus(HttpStatus.CREATED)
    public Language update(@PathVariable ( name = "name") String name, @RequestBody Language language) {
        for (Language lan : languages) {
            if (name.equals(lan.getName())) {
                lan.setName(lan.getName());
                return lan;
            }
        } return null;
    }
    @DeleteMapping("/{name}")
    public Language delete(@PathVariable (name = "name") String name) {
        for (Language language : languages) {
            if (name.equals(language.getName())){
                this.languages.remove(language);
                return language;
            }
        }
        return null;
    }
}
