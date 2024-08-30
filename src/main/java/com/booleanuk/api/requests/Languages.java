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
    public Language getLanguage(@PathVariable("name") String name) {
        return this.languages.stream()
                .filter(loopLanguage -> loopLanguage.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language putLanguage(@PathVariable("name") String name,  @RequestBody Language updatedLanguage ) {
        Language language = this.languages.stream()
                .filter(loopLanguage -> loopLanguage.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
        if(language != null){
            language.setName(updatedLanguage.getName());
            return language;
        }else{
            return null;
        }
    }
    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Language deleteLanguageFirstName(@PathVariable("name") String name) {
        for (int i = 0; i < this.languages.size(); i++) {
            if(this.languages.get(i).getName().equalsIgnoreCase(name)){
                Language deletedLanguage = this.languages.get(i);
                this.languages.remove(i);
                return deletedLanguage;
            }
        }
        return null;
    }

}
