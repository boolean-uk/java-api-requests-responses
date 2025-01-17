package com.booleanuk.api.requests;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("languages")
public class Languages {
    private List<Language> languages = new ArrayList<>() {
        {
            add(new Language("Java"));
            add(new Language("C#"));
        }
    };

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language createLanguage(@RequestBody Language language) {
        languages.add(language);
        return language;
    }

    @GetMapping
    public List<Language> getAll() {
        return languages;
    }

    @GetMapping("/{name}")
    public Language getLanguage(@PathVariable String name) {
        for (Language l : languages) {
            if (l.getName().toLowerCase().equals(name.toLowerCase())) {
                return l;
            }
        }
        return null;
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language updateLanguage(@PathVariable String name, @RequestBody Language updated) {
        for (Language l : languages) {
            if (l.getName().toLowerCase().equals(name.toLowerCase())) {
                l.setName(updated.getName());
                return l;
            }
        }
        return null;
    }

    @DeleteMapping("/{name}")
    public Language removeLanugage(@PathVariable String name) {
        for (int i = 0; i < languages.size(); i++) {
            if (languages.get(i).getName().toLowerCase().equals(name.toLowerCase())) {
                return languages.remove(i);
            }
        }
        return null;
    }
}
