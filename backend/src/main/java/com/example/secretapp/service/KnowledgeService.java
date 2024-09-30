package com.example.secretapp.service;

import com.example.secretapp.entity.Knowledge;
import com.example.secretapp.entity.KnowledgeDto;
import com.example.secretapp.mapper.KnowledgeMapper;
import com.example.secretapp.repository.KnowledgeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KnowledgeService {

    @Autowired
    private KnowledgeRepository knowledgeRepository;

    public List<KnowledgeDto> getAll() {
        return knowledgeRepository.findAll().stream()
                .map(KnowledgeMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public List<KnowledgeDto> search(String term){
        return knowledgeRepository.searchBy(term).stream()
                .map(KnowledgeMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public Knowledge create(Knowledge knowledge) {
        return knowledgeRepository.save(knowledge);
    }

    public Knowledge update(Long id, Knowledge knowledge) {
        Knowledge knowledgeToUpdate = knowledgeRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        knowledgeToUpdate.setTitle(knowledge.getTitle());
        knowledgeToUpdate.setContent(knowledge.getContent());
        knowledgeToUpdate.setAuthor(knowledge.getAuthor());
        knowledgeToUpdate.setLastModifiedDate(knowledge.getLastModifiedDate());
        return knowledgeRepository.save(knowledgeToUpdate);

    }

    public void delete(Long id) {
        knowledgeRepository.deleteById(id);
    }
}
