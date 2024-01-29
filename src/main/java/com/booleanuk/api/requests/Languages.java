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
        languages.add(language);
        return language;
    }

    @GetMapping
    public List<Language> getAll() {
        return languages;
    }

    @GetMapping("/{name}")
    public Language getOne(@PathVariable(name = "name") String name) {
        for(Language language: languages) {
            if(language.getName().equalsIgnoreCase(name)) {
                return language;
            }
        }
        return null;
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language update(@PathVariable String name, @RequestBody Language language) {
        for(Language currentLanguage: languages) {
            if(currentLanguage.getName().equalsIgnoreCase(name)) {
                currentLanguage.setName(language.getName());
                return currentLanguage;
            }
        }
        return null;
    }

    @DeleteMapping("/{name}")
    public Language delete(@PathVariable String name) {
        for(int i = 0; i < languages.size(); i++) {
            if(languages.get(i).getName().equalsIgnoreCase(name)) {
                return languages.remove(i);
            }
        }
        return null;
    }
}
