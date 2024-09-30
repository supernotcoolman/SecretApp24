package com.example.secretapp.mapper;

import com.example.secretapp.entity.Knowledge;
import com.example.secretapp.entity.KnowledgeDto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface KnowledgeMapper {

    KnowledgeMapper INSTANCE = Mappers.getMapper(KnowledgeMapper.class);
    KnowledgeDto toDto(Knowledge knowledge);
}
