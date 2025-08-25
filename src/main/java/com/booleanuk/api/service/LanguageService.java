package com.booleanuk.api.service;

import com.booleanuk.api.model.Language;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LanguageService {

    private List<Language> languages = new ArrayList<>() {{
        add(new Language("Java"));
        add(new Language("C#"));
    }};

    public Language getSpecificLanguage(String name) {
        Language foundLanguage = languages.stream().filter(l -> l.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
        return foundLanguage;
    }

    public Language updateLanguage(String name, Language language) {
        Language foundLanguage = languages.stream().filter(l -> l.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
        if (foundLanguage != null) {
            foundLanguage.setName(language.getName());
        }
        return foundLanguage;
    }

    public Language deleteLanguage(String name) {
        Language foundLanguage = languages.stream().filter(language -> language.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
        if (foundLanguage != null) {
            languages.remove(foundLanguage);
        }
        return foundLanguage;
    }

    public List<Language> getLanguages() {
        return languages;
    }
}
