package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@RestController
@RequestMapping("/languages")
public class Languages {
    private ArrayList<Language> languages;

    public Languages(){
        this.languages = new ArrayList<>();
    }

    @GetMapping
    public ArrayList<Language> getAllLanguages(){
        return this.languages;
    }

    @GetMapping("/{name}")
    public Language getOneLanguage(@PathVariable String name){
        for(Language l : this.languages){
            if(l.getName().equals(name)){
                return l;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language createLanguage(@RequestBody Language language){
        if(language.getName() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        else{
            this.languages.add(language);
            return language;
        }
    }
    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language updateOneLanguage(@PathVariable String name, @RequestBody Language language){
        if(language.getName() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        for(Language l : this.languages){
            if(l.getName().equals(name)){
                l.setName(language.getName());
                return l;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{name}")
    public Language deleteOneLanguage(@PathVariable String name){
        Iterator<Language> i = this.languages.iterator();
        while (i.hasNext()){
            Language language = i.next();
            if(language.getName().equals(name)){
                i.remove();
                return language;
            }
        }
         throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
