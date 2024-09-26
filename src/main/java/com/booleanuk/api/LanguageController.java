package com.booleanuk.api;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("languages")
public class LanguageController {

    public LanguageController(){

    }

    private List<Language> languages = new ArrayList<>(){{
        add(new Language("Java"));
        add(new Language("C#"));
    }};
    @GetMapping("languages/{id}")
    public String getOneLanguage(@PathVariable int id){
        if ( id < this.languages.size()){
            return this.languages.get(id).getName();
        }
        return null;
    }
    @GetMapping
    public List<Language> getAllLanguage(){
        return this.languages;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language create(@RequestBody Language author){
        this.languages.add(author);
        return author;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language update(@PathVariable int id, @RequestBody Language language){
        if (id < this.languages.size()){
            this.languages.get(id).setName(language.getName());
            return this.languages.get(id);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Language delete(@PathVariable int id){
        if (id < this.languages.size()){
            return this.languages.remove(id);
        }
        return null;
    }
}
