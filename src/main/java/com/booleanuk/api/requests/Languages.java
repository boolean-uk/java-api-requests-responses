package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public List<Language> getAll() {
        return this.languages;
    }

    @GetMapping("/{name}")
    public Language getSpecificLanguage(@PathVariable String name) {
        for (Language l : this.languages)
        {
            if(l.getName().equals(name))
            {
                return l;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language create(@RequestBody Language language)
    {
        this.languages.add(language);
        return language;
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language update(@PathVariable String name, @RequestBody Language language)
    {
        for(Language l : this.languages)
        {
            if(l.getName().equals(name))
            {
                l.setName(language.getName());
                return l;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{name}")
    public Language delete(@PathVariable String name) {
        for(Language l : this.languages)
        {
            if(l.getName().equals(name))
            {
                this.languages.remove(l);
                return l;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
