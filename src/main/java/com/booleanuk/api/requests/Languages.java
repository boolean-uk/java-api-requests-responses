package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("languages")
public class Languages {
    private HashMap<String, Language> languages = new HashMap<>(){{
        put("Java", new Language("Java"));
        put("C#", new Language("C#"));
    }};

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language create(@RequestBody Language language) {
        this.languages.put(language.getName(), language);
        return language;
    }

    @GetMapping
    public HashMap<String, Language> getAll(){
        return this.languages;
    }

    @GetMapping("{id}")
    public Language getSpecific(@PathVariable String id) {
        Language language = this.languages.get(id);
        if (language != null) {
            return language;
        }
        return null;
    }

    @PutMapping("{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language update(
            @PathVariable String name,
            @RequestBody Language language) {

        if (this.languages.get(name) != null) {
            languages.put(language.getName(), language);
            return language;
        }
        return null;
    }

    @DeleteMapping("{name}")
    public Language delete(@PathVariable String name) {
        Language language = this.languages.get(name);
        if (language != null) {
            languages.remove(name);
            return language;
        }
        return null;
    }
}
