package com.example.secretapp.repository;

import com.example.secretapp.entity.Knowledge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KnowledgeRepository extends JpaRepository<Knowledge, Long> {

    @Query("select k from Knowledge k where k.title like %:term% or k.content like %:term% or k.author like %:term%")
    public List<Knowledge> searchBy(@Param("term") String term);
}
