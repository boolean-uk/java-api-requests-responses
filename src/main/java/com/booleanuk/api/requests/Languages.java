package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.naming.ldap.LdapName;
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
    public List<Language> getLanguages() {
        return this.languages;
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Language getSpecificLanguage(@PathVariable String name) {
        for(Language lang : languages) {
            if(lang.getName().equalsIgnoreCase(name)) {
                return lang;
            }
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Language not found");
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language updateSpecificLanguage(@PathVariable String name) {
        for(Language lang : languages) {
            if(lang.getName().equalsIgnoreCase(name)) {
                lang.setName(name);
                return lang;
            }
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Language already exists");

    }


    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Language deleteLanguageByFirstName(@PathVariable String name) {
        for(Language lang : this.languages) {
            if(lang.getName().equalsIgnoreCase(name)) {
                this.languages.remove(lang);
                return lang;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Language not found by provided first name");
    }


}
