package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("languages")
public class LanguageController {
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
    public List<Language> getAllLanguages(){
        return languages;
    }

    @GetMapping("/{name}")
    public Language getOneLanguage(@PathVariable String name){
        return getLanguage(name);
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language updateLanguage(@PathVariable String name, @RequestBody Language updatedLanguage){
        Language language = getLanguage(name);

        if (language != null){
            int index = languages.indexOf(language);
            languages.get(index).setName(updatedLanguage.getName());

            return languages.get(index);
        }
        return null;
    }

    @DeleteMapping("/{name}")
    public Language deleteLanguage(@PathVariable String name){
        Language language = getLanguage(name);

        if (language != null){
            int index = languages.indexOf(language);

            return languages.remove(index);
        }
        return null;
    }

    private Language getLanguage(String name){
        for (Language language : languages){
            if (language.getName().equals(name)){
                return language;
            }
        }
        return null;
    }
}
