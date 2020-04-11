package com.quesandans.restservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.quesandans.restservice.dao.QuestionsRepository;
import com.quesandans.restservice.exception.RecordNotFoundException;
import com.quesandans.restservice.model.QuestionEntity;
import com.quesandans.restservice.model.QuestionPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    QuestionsRepository repository;

    public List<QuestionEntity> getAllQuestions()
    {
        List<QuestionEntity> questionList = repository.findAll();

        if(questionList.size() > 0) {
            return questionList;
        } else {
            return new ArrayList<QuestionEntity>();
        }
    }
    public QuestionEntity getQuestionById(Long id) throws RecordNotFoundException
    {
        Optional<QuestionEntity> question = repository.findById(id);

        if(question.isPresent()) {
            return question.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }


    public QuestionEntity createOrUpdateQuestion(QuestionEntity entity) throws RecordNotFoundException
    {
            entity = repository.save(entity);
            return entity;
    }

}
