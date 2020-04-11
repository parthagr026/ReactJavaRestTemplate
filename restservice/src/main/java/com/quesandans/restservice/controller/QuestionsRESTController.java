package com.quesandans.restservice.controller;

import java.util.List;

import com.quesandans.restservice.dao.QuestionsRepository;
import com.quesandans.restservice.exception.RecordNotFoundException;
import com.quesandans.restservice.model.QuestionEntity;
import com.quesandans.restservice.model.QuestionPojo;
import com.quesandans.restservice.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/questions", produces = { MediaType.APPLICATION_JSON_VALUE })
public class QuestionsRESTController {

    @Autowired
    QuestionService service;

    @GetMapping("/getAllQuestions")
    public ResponseEntity<List<QuestionEntity>> getAllQuestions() {
        List<QuestionEntity> list = service.getAllQuestions();
        return new ResponseEntity<List<QuestionEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("getQuestionById/{id}")
    public ResponseEntity<QuestionEntity> getEmployeeById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        QuestionEntity entity = service.getQuestionById(id);

        return new ResponseEntity<QuestionEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }


    @PostMapping("/postQuestion")
    public ResponseEntity<QuestionEntity> createOrUpdateEmployee(@RequestBody QuestionEntity question)
            throws RecordNotFoundException {
        QuestionEntity updated = service.createOrUpdateQuestion(question);
        return new ResponseEntity<QuestionEntity>(updated, new HttpHeaders(), HttpStatus.OK);
    }
}
