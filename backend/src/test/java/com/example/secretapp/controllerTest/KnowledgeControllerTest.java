package com.example.secretapp.controllerTest;

import com.example.secretapp.controller.KnowledgeController;
import com.example.secretapp.entity.Knowledge;
import com.example.secretapp.service.KnowledgeService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

@WebMvcTest(controllers = KnowledgeController.class)
public class KnowledgeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private KnowledgeService knowledgeService;

    private Knowledge knowledge1;
    private Knowledge knowledge2;

    @BeforeEach
    public void setUp() {
        knowledge1 = Knowledge.builder()
                .title("Title 1")
                .content("Content 1")
                .author("Author 1")
                .build();

        knowledge2 = Knowledge.builder()
                .title("Title 2")
                .content("Content 2")
                .author("Author 2")
                .build();

    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/api/knowledge").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("Test 1")))
                .andExpect(jsonPath("$[1].title", is("Test 2")));
    }

    @Test
    public void testCreateKnowledge() throws Exception {

        Mockito.when(knowledgeService.create(Mockito.any(Knowledge.class))).thenReturn(knowledge1);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/knowledge")
                        .content(objectMapper.writeValueAsString(knowledge1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Title 1"))
                .andExpect(jsonPath("$.author").value("Author 1"))
                .andExpect(jsonPath("$.content").value("Content 1"))
                .andDo(print());

    }
}