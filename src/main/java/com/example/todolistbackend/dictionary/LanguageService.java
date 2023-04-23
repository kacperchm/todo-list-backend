package com.example.todolistbackend.dictionary;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {

    private LanguageRepository repository;

    public LanguageService(LanguageRepository repository) {
        this.repository = repository;
    }

    public List<Language> list() {
        return repository.findAll();
    }

    public long countLanguages() {
        return repository.count();
    }

    public Language getLanguage(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteLanguage(Integer id) {
        repository.deleteById(id);
    }

    public Language createLanguage(Language language) {
        Language lang = new Language();
        lang.setCode(language.getCode());
        lang.setName(language.getName());
        return repository.save(lang);
    }

    public Language updateLanguage(Language language) {
        Language langUpdate = repository.findById(language.getId()).orElseThrow();
        langUpdate.setName(language.getName());
        langUpdate.setCode(language.getCode());
        return repository.save(langUpdate);
    }
}
