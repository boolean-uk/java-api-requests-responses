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

    @GetMapping("/{languageCheck}")
    public Language getOne(@PathVariable(name = "languageCheck") String languageCheck) {
        for(Language language1 : languages){
            if (language1.getName().equals(languageCheck)){
                return language1;
            }
        }
        return null;
    }

    @PutMapping("/{languageCheck}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language update(@PathVariable(name = "languageCheck") String languageCheck,@RequestBody Language language) {
        for(Language language1 : languages){
            if (language1.getName().equals(languageCheck)){
                language1.setName(language.getName());
                return language1;
            }
        }
        return null;
    }

    @DeleteMapping("/{languageCheck}")
    public Language delete(@PathVariable (name = "languageCheck") String languageCheck) {
        for(Language language1 : languages){
            if (language1.getName().equals(languageCheck)){
                languages.remove(language1);
                return language1;
            }
        }
        return null;
    }
}
