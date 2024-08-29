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
    @ResponseStatus(HttpStatus.OK)
    public List<Language> getAll(){
        return this.languages;
    }

    @GetMapping("{nameOfLanguage}")
    @ResponseStatus(HttpStatus.OK)
    public Language getSpecificLanguage(@PathVariable String nameOfLanguage){
        return this.languages.stream()
                .filter(language -> language.getName().equals(nameOfLanguage))
                .findFirst()
                .orElse(null);
    }

    @PutMapping("/{nameOfLanguage}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language update(@PathVariable String nameOfLanguage, @RequestBody Language language){
        for (int i = 0; i < languages.size(); i++) {
            if (languages.get(i).getName().equals(nameOfLanguage)) {
                languages.set(i, language);
                return language;
            }
        }
        return null;
    }

    @DeleteMapping("{nameOfLanguage}")
    @ResponseStatus(HttpStatus.OK)
    public Language remove(@PathVariable String nameOfLanguage){
        for (int i = 0; i < languages.size(); i++) {
            if (languages.get(i).getName().equals(nameOfLanguage)) {
                return languages.remove(i);
            }
        }
        return null;
    }

}
