package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/languages")
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
    public List<Language> getAll() {
        return this.languages;
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Language getLanguage(@PathVariable(name = "name") String name) {
        for (Language l : languages) {
            if (l.getName().equals(name)) {
                return l;
            }
        }
        return null;
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language updateLanguage(
            @PathVariable(name = "name") String name,
            @RequestBody Language newLanguage) {
        for (int i=0; i<languages.size(); i++) {
            if (languages.get(i).getName().equals(name)) {
                languages.set(i, newLanguage);
                return languages.get(i);
            }
        }
        return null;
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Language deleteLanguage(@PathVariable(name = "name") String name) {
        boolean foundLanguage = false;
        int i;
        for (i = 0; i < languages.size(); i++) {
            if (languages.get(i).getName().equals(name)) {
                foundLanguage = true;
                break;
            }
        }
        if (foundLanguage) {
            return languages.remove(i);
        }
        return null;
    }
}
