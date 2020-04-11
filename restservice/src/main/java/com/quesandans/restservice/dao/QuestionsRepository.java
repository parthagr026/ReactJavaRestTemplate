package com.quesandans.restservice.dao;

import com.quesandans.restservice.model.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionsRepository extends JpaRepository<QuestionEntity,Long>{

    List<QuestionEntity> findAll();
    Optional<QuestionEntity> findById(long Id);

}
