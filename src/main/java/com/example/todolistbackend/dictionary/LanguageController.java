package com.example.todolistbackend.dictionary;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dictionary/language")
public class LanguageController {

    private LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping
    public List<Language> list() {
        return languageService.list();
    }

    @GetMapping("/property/count")
    public long count() {
        return languageService.countLanguages();
    }

    @GetMapping("/{id}")
    public Language getLang(@PathVariable Integer id) {
        return languageService.getLanguage(id);
    }

    @DeleteMapping("/{id}")
    public void deleteLang(@PathVariable Integer id) {
        languageService.deleteLanguage(id);
    }

    @PostMapping
    public Language createLang(@RequestBody Language language) {
        return languageService.createLanguage(language);
    }

    @PutMapping
    public Language updateLang(@RequestBody Language language) {
        return languageService.updateLanguage(language);
    }
}
