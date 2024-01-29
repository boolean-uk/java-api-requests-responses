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

    // Create a language
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language createLanguage(@RequestBody Language language) {
        this.languages.add(language);
        return this.languages.get(this.languages.indexOf(language));
    }
    // Return all languages
    @GetMapping
    public List<Language> getAll() {
        return this.languages;
    }

    // Get a language
    @GetMapping("/{name}")
    public Language getLanguage(@PathVariable(name="name") String name) {
        for(Language l : languages){
            if(l.getName().equals(name)){
                return l;
            }
        }
        return null;
    }
    // Update a language

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language updateLanguage(@PathVariable(name="name") String name, @RequestBody Language language) {
        for(Language l : languages) {
            if(l.getName().equals(name)) {
                int index = this.languages.indexOf(l);
                this.languages.set(index, language);
                return this.languages.get(index);
            }
        }
        return null;
    }


    @DeleteMapping("/{name}")
    public Language deleteLanguage(@PathVariable(name="name") String name) {
        for(Language l : languages){
            if(l.getName().equals(name)){
                int index = this.languages.indexOf(l);
                return this.languages.remove(index);
            }
        }
        return null;

    }

}
