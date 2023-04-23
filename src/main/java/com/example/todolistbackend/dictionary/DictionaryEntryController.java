package com.example.todolistbackend.dictionary;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dictionary/entry")
public class DictionaryEntryController {

    private DictionaryEntryService service;

    public DictionaryEntryController(DictionaryEntryService service) {
        this.service = service;
    }

    @GetMapping
    public List<DictionaryEntry> list() {
        return service.List();
    }

    @GetMapping("/property/count")
    public long count(@RequestParam(required = false) String code) {
        return service.countDictEntry(code);
    }

    @GetMapping("/operation/search")
    public List<DictionaryEntry> search(@RequestParam String text){
        return service.search(text);
    }

    @GetMapping("/{id}")
    public DictionaryEntry getEntry(@PathVariable Integer id) {
        return service.getDictEntry(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEntry(@PathVariable Integer id) {
        service.deleteDictEntry(id);
    }

    @PostMapping
    public DictionaryEntry createEntry(@RequestBody DictionaryEntry dictionaryEntry) {
        return service.createDictEntry(dictionaryEntry);
    }

    @PutMapping
    public DictionaryEntry updateEntry(@RequestBody DictionaryEntry dictionaryEntry) {
        return service.updateDictEntry(dictionaryEntry);
    }
}
