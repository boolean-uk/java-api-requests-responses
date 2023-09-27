package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("languages")
public class Languages {
    private List<Language> languages = new ArrayList<>() {{
        add(new Language("Java"));
        add(new Language("C#"));
    }};


    @GetMapping
    public List<Language> getAll() {
        return this.languages;
    }

    @GetMapping("/{name}")
    public Language getOne(@PathVariable(name = "name") String name) {
        for (Language language : languages) {
            if (language.getName().equals(name)) {
                return language;
            }
        }
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language create(@RequestBody Language language) {
        if (!languages.contains(language) && language.getName() != null) {
            this.languages.add(language);
            return language;
        }
        return null;
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language update(@PathVariable(name = "name") String name, @RequestBody Language language) {
        for (Language stu : languages) {
            if (stu.getName().equals(name)) {
                stu.setName(language.getName());
                return stu;
            }
        }
        return null;
    }

    @DeleteMapping("/{name}")
    public Language delete(@PathVariable(name = "name") String langName) {
        //Never delete an item directly using a loop, add it to a var instead
        //Then delete the added var later on with an if statement
        Language languageToRemove = null;

        for (Language stu : languages) {
            if (stu.getName().equals(langName)) {
                languageToRemove = stu;
                break;
            }
        }

        if (languageToRemove != null) {
            languages.remove(languageToRemove);
            return languageToRemove;
        } else {
            return null;
        }
    }




}
