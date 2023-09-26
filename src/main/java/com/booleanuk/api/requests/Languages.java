package com.booleanuk.api.requests;

import com.booleanuk.api.exceptions.BadRequestException;
import com.booleanuk.api.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.StringUtils.hasLength;

@RestController
@RequestMapping("/languages")
public class Languages {
    private List<Language> languages = new ArrayList<>() {{
        add(new Language("Java"));
        add(new Language("C#"));
    }};

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language create(@RequestBody Language language) {
        if (!hasLength(language.getName())) {
            throw new BadRequestException("Name is required.");
        }
        this.languages.add(language);
        return language;
    }

    @GetMapping
    public List<Language> getAll() {
        return this.languages;
    }

    @GetMapping("/{name}")
    public Language getSpecificLanguage(@PathVariable String name) {
        return this.languages.stream()
                .filter(language -> language.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Language not found"));
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language update(@PathVariable String name, @RequestBody Language language) {
        if (!hasLength(language.getName())) {
            throw new BadRequestException("Name is required.");
        }
        Optional<Language> foundLanguage = this.languages.stream()
                .filter(l -> l.getName().equalsIgnoreCase(name))
                .findFirst();
        if (foundLanguage.isPresent()) {
            Language existingLanguage = foundLanguage.get();
            existingLanguage.setName(language.getName());
            return existingLanguage;
        } else {
            throw new NotFoundException("Language not found");
        }
    }

    @DeleteMapping("/{name}")
    public Language delete(@PathVariable String name) {
        Optional<Language> foundLanguage = this.languages.stream()
                .filter(l -> l.getName().equalsIgnoreCase(name))
                .findFirst();

        if (foundLanguage.isPresent()) {
            Language deletedLanguage = foundLanguage.get();
            this.languages.remove(deletedLanguage);
            return deletedLanguage;
        } else {
            throw new NotFoundException("Language not found");
        }
    }
}
