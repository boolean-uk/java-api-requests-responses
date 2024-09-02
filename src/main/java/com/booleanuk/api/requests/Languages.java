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


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Language> getAllLanguages(){
        return this.languages;
    }

    @GetMapping("/{languageName}")
    @ResponseStatus(HttpStatus.OK)
    public Language getSpecificLanguage(@PathVariable String languageName){
        return getLanguageByName(languageName);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language create(@RequestBody Language language) {
        this.languages.add(language);

        return language;
    }

    @PutMapping("{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language update(@PathVariable String name, @RequestBody Language language){
        for(Language l: languages){
            if(name.equals(l.getName())){
                l.setName(language.getName());
                return language;
            }
        }
        return null;
    }

    @DeleteMapping("{name}")
    public Language delete(@PathVariable String name){
        for (Language l: languages){
            if (l.getName().equals(name)){
                languages.remove(l);
                return l;
            }
        }
        return null;
    }

    public Language getLanguageByName(String name){
        for(Language l: languages){
            if(l.getName().equals(name)){
                return l;
            }
        }
        return null;
    }

}
