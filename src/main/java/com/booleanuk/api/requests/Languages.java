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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language create(@RequestBody Language language) {
        languages.add(language);
        return language;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Language> getLanguages() {
        return languages;
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Language getLanguage(@PathVariable String name) {
        for (int i = 0; i < languages.size(); i++) {
            if (languages.get(i).getName().equals(name)) {
                return languages.get(i);
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Language not found");
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Language update(@PathVariable String name, @RequestBody Language language) {
        for (int i = 0; i < languages.size(); i++) {
            if (languages.get(i).getName().equals(name)) {
                languages.get(i).setName(language.getName());
                return languages.get(i);
            }
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Language not found");
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Language delete(@PathVariable String name) {
        for (int i = 0; i<languages.size(); i++) {
            if (languages.get(i).getName().equals(name)) {
                return languages.remove(i);
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Language not found");
    }

}
