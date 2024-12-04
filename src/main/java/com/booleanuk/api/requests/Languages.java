package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.naming.ldap.LdapName;
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
    public List<Language> getLanguages() {
        return this.languages;
    }

    @GetMapping("/{name}")
    public Language getLanguage(@PathVariable (name="name") String name) {
        Optional<Language> result = this.languages.stream()
                .filter(lang -> lang.getName().equals(name))
                .findFirst();

        return result.orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language create(@RequestBody Language language) {
        this.languages.add(language);
        return language;
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language update(@RequestBody Language language, @PathVariable (name = "name") String name) {
        for (Language lang: languages) {
            if (lang.getName().equals(name)) {
                lang.setName(language.getName());
                return lang;
            }
        }
        return null;
    }

    @DeleteMapping("/{name}")
    public Language delete(@PathVariable (name = "name") String name) {
        for (Language lang: languages) {
            if (lang.getName().equals(name)) {
                languages.remove(lang);
                return lang;
            }
        }
        return null;
    }
}
