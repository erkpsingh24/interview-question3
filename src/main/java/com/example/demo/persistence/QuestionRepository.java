package com.example.demo.persistence;

import com.example.demo.model.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 Persistence layer to add and fetch Questions
 */
@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

}
