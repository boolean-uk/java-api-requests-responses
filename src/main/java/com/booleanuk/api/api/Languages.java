package com.booleanuk.api.api;

import com.booleanuk.api.Response;
import com.booleanuk.api.requests.Language;
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
    public Object getLanguageByName(@PathVariable String name) {
        Language language = null;
        for (Language item : this.languages) {
            if (item.getName().equals(name)) {
                language = item;
                break;
            }
        }
        return language != null ? language : Response.notFound("No language with name " + name + " exists.");
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Object updateLanguage(@PathVariable String name, @RequestBody Language body) {
        boolean exists = this.languages.stream().anyMatch(s -> s.getName().equals(name));
        if (!exists) {
            return Response.notFound("No language named " + name + " exists");
        }

        Language updatedLanguage = null;
        for (Language item : this.languages) {
            if (item.getName().equals(name)) {
                this.languages.remove(item);

                updatedLanguage = new Language(body.getName());
                this.languages.add(updatedLanguage);
                break;
            }
        }
        return updatedLanguage;
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Object deleteLanguage(@PathVariable String name) {
        boolean exists = this.languages.stream().anyMatch(l -> l.getName().equals(name));
        if (!exists) {
            return Response.notFound("No language named " + name + " exists");
        }

        Language deletedLanguage = null;
        for (Language item : this.languages) {

            if (item.getName().equals(name)) {
                deletedLanguage = item;
                this.languages.remove(item);
                break;

            }
        }

        return deletedLanguage;
    }
}
