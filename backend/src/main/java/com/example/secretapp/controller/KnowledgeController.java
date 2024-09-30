package com.example.secretapp.controller;

import com.example.secretapp.entity.Knowledge;
import com.example.secretapp.entity.KnowledgeDto;
import com.example.secretapp.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/knowledge")
public class KnowledgeController {

    @Autowired
    private KnowledgeService knowledgeService;

    @GetMapping
    public List<KnowledgeDto> getAll() {
        return knowledgeService.getAll();
    }

    @GetMapping("search")
    public List<KnowledgeDto> searchByTerm(@RequestParam("term") String term) {
        return knowledgeService.search(term);
    }

    @PostMapping
    public Knowledge add(@RequestBody Knowledge knowledge) {
        return knowledgeService.create(knowledge);
    }

    @PutMapping("{id}")
    public Knowledge update(@PathVariable("id") Long id, @RequestBody Knowledge knowledge) {
        return knowledgeService.update(id, knowledge);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        knowledgeService.delete(id);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
