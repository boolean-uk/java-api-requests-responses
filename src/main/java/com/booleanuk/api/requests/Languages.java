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
        if (!this.languages.contains(language))  {
            this.languages.add(language);
            return language;
        }
        return null;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Language> getAll() {
        return this.languages;
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Language getOneLanguage(@PathVariable String name) {
        return this.languages.stream().filter(l -> l.getName().equals(name)).findFirst().orElse(null);
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language update(@PathVariable String name, @RequestBody Language language) {
        Language languageToUpdate = this.languages.stream().filter(l -> l.getName().equals(name)).findFirst().orElse(null);
        if(languageToUpdate != null) {
            languageToUpdate.setName(language.getName());
        }
        return languageToUpdate;
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Language delete(@PathVariable String name) {
        Language languageToDelete = this.languages.stream().filter(l -> l.getName().equals(name)).findFirst().orElse(null);
        if (languageToDelete != null) {
            this.languages.remove(languageToDelete);
        }
        return languageToDelete;
    }

}
