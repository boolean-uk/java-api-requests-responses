package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("languages")
public class Languages {
    private final List<Language> languages = new ArrayList<>(){{
        add(new Language("Java"));
        add(new Language("C#"));
    }};

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language create(@RequestBody Language lang) {
        if (findLanguage(lang.name) > 0) return null; // prevent duplicate languages
        languages.add(lang);
        return lang;
    }

    @GetMapping
    public List<Language> getAllLanguages() {
        return languages;
    }

    @GetMapping("/{language}")
    public Language getLanguage(@PathVariable String language) {
        final int _langIndex = findLanguage(language);
        return _langIndex >= 0 ? languages.get(_langIndex) : null;
    }

    @PutMapping("/{language}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language update(@PathVariable String language, @RequestBody Language lang) {
        final int _langIndex = findLanguage(language);
        if (_langIndex < 0) return null;

        languages.get(_langIndex).name = lang.name;

        return languages.get(_langIndex);
    }

    @DeleteMapping("/{language}")
    public Language removeLanguage(@PathVariable String language) {
        final int _langIndex = findLanguage(language);
        return _langIndex >= 0 ? languages.remove(_langIndex) : null;
    }

    private int findLanguage(final String language) {
        for (int i = 0; i < languages.size(); i++)
            if (languages.get(i).name.equals(language))
                return i;
        return -1;
    }
}
