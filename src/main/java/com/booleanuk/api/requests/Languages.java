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


    @GetMapping
    public List<Language> getLanguages(){
        return languages;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language addLanguage(@RequestBody Language lang){
        languages.add(lang);

        return lang;
    }


    @GetMapping("{name}")
    public Language getLanguage(@PathVariable String name){
        for(Language l: languages){
            if(l.getName().equals(name)){
                return l;
            }
        }
        return null;
    }


    @PutMapping("{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language putLangugage(@PathVariable String name, @RequestBody Language lang){;
        for (int i = 0; i < languages.size(); i++) {
            if (languages.get(i).getName().equals(name)){
                languages.remove(i);
                languages.add(i, lang);
                break;
            }
        }
        return lang;
    }

    @DeleteMapping("{name}")
    public Language deleteLanguage(@PathVariable String name){
        for (int i = 0; i < languages.size(); i++) {
            if (languages.get(i).getName().equals(name)){
                return languages.remove(i);
            }
        }
        return null;
    }
}
