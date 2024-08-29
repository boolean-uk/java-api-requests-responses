package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PutExchange;

import java.util.HashMap;
import java.util.Random;

@RestController
@RequestMapping("languages")
public class Languages {
    private HashMap<String,Language> languages;
    private Random rand = new Random();
    private String alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public Languages() {
        languages = new HashMap<>() {{
            put(generateId(), new Language("Java"));
            put(generateId(), new Language("C#"));
        }};
    }

    private String generateId() {
        int length = 4;
        StringBuilder newId = new StringBuilder();
        for (int i = 0; i < length; i++){
            int randomIndex = this.rand.nextInt(this.alphaNumeric.length());
            newId.append(this.alphaNumeric.charAt(randomIndex));
        }
        return newId.toString();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language create(@RequestBody Language lang) {
        if(lang.getName().isEmpty()) {
            return null;
        }
        String id = generateId();
        this.languages.put(id, lang);

        return lang;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public HashMap<String, Language> getLanguages() {
        return this.languages;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Language getLanguageWithId(@PathVariable String id) {
        return languages.get(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language deleteLanguageWithId(@PathVariable String id) {
        Language deleteLang = this.languages.get(id);
        this.languages.remove(id);
        return deleteLang;
    }

    @PutExchange("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language updateLanguage(@PathVariable String id, @RequestBody Language newLang){
        this.languages.put(id, newLang);

        return this.languages.get(id);
    }

}
