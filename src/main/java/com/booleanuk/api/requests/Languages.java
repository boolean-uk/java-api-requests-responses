package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("languages")
public class Languages {
    private List<Language> languages = new ArrayList<>(){{
        add(new Language("Java"));
        add(new Language("C#"));
    }};

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Language> getLanguages() {
        return languages;
    }

    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public Language getLanguage(@PathVariable(name="name") String name) {
        Optional<Language> l = languages.stream().filter(lang -> lang.getName().equals(name)).findFirst();
        return l.orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language postLanguage(@RequestBody Language language) {
        this.languages.add(language);
        return this.languages.get(this.languages.size() - 1);
    }

    @PutMapping("{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language language(@PathVariable(name="name") String name, @RequestBody Language language) {
        Optional<Language> l = languages.stream().filter(lang -> lang.getName().equals(name)).findFirst();
        if (l.isEmpty()) return null;
        l.get().setName(language.getName());
        return l.get();
    }

    @DeleteMapping("{name}")
    public Language deleteLanguage(@PathVariable(name="name") String name) {
        Optional<Language> l = languages.stream().filter(lang -> lang.getName().equals(name)).findFirst();
        if (l.isEmpty()) return null;
        languages.remove(l.get());
        return l.get();
    }
}
