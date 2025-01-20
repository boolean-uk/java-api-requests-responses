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
    public List<Language> getAll() {return this.languages;}

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Language getOne(@PathVariable String name) {
        for (Language language : this.languages) {
            if (language.getName().equals(name)) {
                return language;
            }
        }
        return null;
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language update(@PathVariable String name, @RequestBody Language language) {
        for (Language current : languages) {
            if (current.getName().equals(name)) {
                current.setName(language.getName());
                return current;
            }
        }
        return null;
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Language delete(@PathVariable String name) {
        Language language = this.getOne(name);
        if (language != null) {
            this.languages.remove(language);
        }
        return language;
    }
}
