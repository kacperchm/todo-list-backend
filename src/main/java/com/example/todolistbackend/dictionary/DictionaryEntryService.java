package com.example.todolistbackend.dictionary;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryEntryService {
    private DictionaryEntryRepository repository;

    public DictionaryEntryService(DictionaryEntryRepository repository) {
        this.repository = repository;
    }

    public List<DictionaryEntry> List() {
        return repository.findAll();
    }

    public long countDictEntry(String code) {
        if(code != null) {
            return repository.countDictionaryEntriesByLanguage_Code(code);
        }
            return repository.count();
    }

    public List<DictionaryEntry> search(String text) {
        return repository.findAllByWordContainingOrTranslationContainingOrDescriptionContaining(text,text,text);
    }

    public DictionaryEntry getDictEntry(Integer id) {
        return repository.findById(id).orElseThrow();
    }

    public void deleteDictEntry(Integer id) {
        repository.deleteById(id);
    }

    public DictionaryEntry createDictEntry(DictionaryEntry entry) {
        DictionaryEntry dictionaryEntry = new DictionaryEntry();
        dictionaryEntry.setDescription(entry.getDescription());
        dictionaryEntry.setLanguage(entry.getLanguage());
        dictionaryEntry.setWord(entry.getWord());
        dictionaryEntry.setTranslation(entry.getTranslation());
        return repository.save(dictionaryEntry);
    }

    public DictionaryEntry updateDictEntry(DictionaryEntry entry) {
        DictionaryEntry updatedDictionaryEntry = repository.findById(entry.getId()).orElseThrow();
        updatedDictionaryEntry.setDescription(entry.getDescription());
        updatedDictionaryEntry.setLanguage(entry.getLanguage());
        updatedDictionaryEntry.setWord(entry.getWord());
        updatedDictionaryEntry.setTranslation(entry.getTranslation());
        return repository.save(updatedDictionaryEntry);
    }
}
