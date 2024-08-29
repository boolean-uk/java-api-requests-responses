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
    public Language createLanguages(@RequestBody Language language) {
        this.languages.add(language);

        return language;
    }

    @GetMapping
    public List<Language> getAllLanguages() {
        return languages;
    }

    @GetMapping("{name}")
    public Language getAStudent(@PathVariable String name) {
        for(int i = 0; i < languages.size(); i++) {
            if(languages.get(i).getName().equals(name)) {
                return languages.get(i);
            }
        }
        return null;
    }

    @PutMapping("{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language updateLanguage(@PathVariable String name, @RequestBody Language language) {
        for (int i = 0; i < languages.size(); i++) {
            if (languages.get(i).getName().equals(name)) {
                languages.get(i).setName(language.getName());
                return languages.get(i);
            }
        }
        return null;
    }

    @DeleteMapping("{name}")
    public Language deleteLanguage(@PathVariable String name){
        for (int i = 0; i < languages.size(); i++) {
            if (languages.get(i).getName().equals(name)) {
                return languages.remove(i);
            }
        }
        return null;
    }
}
