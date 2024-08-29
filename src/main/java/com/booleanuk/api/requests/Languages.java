package com.booleanuk.api.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("languages")
public class Languages {
    private static final Logger log = LoggerFactory.getLogger(Languages.class);
    private List<Language> languages = new ArrayList<>(){{
        add(new Language("Java"));
        add(new Language("C#"));
    }};

    @GetMapping
    public Language[] getLanguages() {
        return languages.toArray(new Language[0]);
    }

    @GetMapping("{name}")
    public Language getLanguage(@PathVariable String name) {
        try {
            return languages.stream().filter(n -> n.getName().equals(name)).findFirst().orElseThrow();
        } catch (Exception e) {
            log.error("e: ", e);
            return null;
        }
    }

    @PutMapping("{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language modifyLanguage(@PathVariable String name, @RequestBody Language language) {
        try {
            Language l = languages.stream().filter(n -> n.getName().equals(name)).findFirst().orElseThrow();
            l.setName(language.getName());
            return language;
        } catch (Exception e) {
            log.error("e: ", e);
            return null;
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language postLanguage(@RequestBody Language language) {
        languages.add(language);
        return language;
    }

    @DeleteMapping("{name}")
    public Language deleteLanguage(@PathVariable String name) {
        try {
            Language l = languages.stream().filter(n -> n.getName().equals(name)).findFirst().orElseThrow();
            languages.remove(l);
            return l;
        } catch (Exception e) {
            log.error("e: ", e);
            return null;
        }
    }
}
