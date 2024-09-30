package com.example.secretapp.serviceTest;


import com.example.secretapp.entity.Knowledge;
import com.example.secretapp.entity.KnowledgeDto;
import com.example.secretapp.repository.KnowledgeRepository;
import com.example.secretapp.service.KnowledgeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class serviceTest {

    @Mock
    private KnowledgeRepository knowledgeRepository;

    @InjectMocks
    private KnowledgeService service;

    @Test
    public void getTest() {
        Knowledge knowledge1 = Knowledge.builder()
                .title("Title 1")
                .content("Content 1")
                .author("Author 1")
                .build();

        Knowledge knowledge2 = Knowledge.builder()
                .title("Title 2")
                .content("Content 2")
                .author("Author 2")
                .build();

        List<Knowledge> knowledgeList = Arrays.asList(knowledge1, knowledge2);

        Mockito.when(knowledgeRepository.findAll()).thenReturn(knowledgeList);

        List<KnowledgeDto> result = service.getAll();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Title 1", result.get(0).getTitle());
        Assertions.assertEquals("Title 2", result.get(1).getTitle());

        Mockito.verify(knowledgeRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void createTest() {
        Knowledge knowledge1 = Knowledge.builder()
                .title("Test 1")
                .content("Content 1")
                .author("Author 1")
                .build();

        Mockito.when(knowledgeRepository.save(Mockito.any(Knowledge.class))).thenReturn(knowledge1);

        Knowledge savedKnowledge = service.create(knowledge1);

        Assertions.assertNotNull(savedKnowledge);
    }
}
