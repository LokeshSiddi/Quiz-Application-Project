package com.lokesh.Quiz_Application_Project.service;

import com.lokesh.Quiz_Application_Project.dto.QuestionDto;
import com.lokesh.Quiz_Application_Project.model.Question;
import com.lokesh.Quiz_Application_Project.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;


    public ResponseEntity<List<QuestionDto>> getAllQuestion() {

        try {
            return new ResponseEntity<>(questionRepository.findAll().stream()
                    .filter(question -> question != null)
                    .map(this::getQuestionDta)
                    .collect(Collectors.toList()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<QuestionDto> getQuestionById(int id) {

        try {
            Question question = questionRepository.getReferenceById(id);
            if(question == null) {
                return new ResponseEntity<>(new QuestionDto(), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(getQuestionDta(question), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new QuestionDto(), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<QuestionDto>> getQuestionByCategory(String category) {
        try {
            return new ResponseEntity<>(questionRepository.findByCategory(category)
                    .stream().filter(q -> q != null)
                    .map(this::getQuestionDta).collect(Collectors.toList()),
                    HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<QuestionDto>> getQuestionByDifficulty(String difficulty) {
        try {
            return new ResponseEntity<>(questionRepository.findByDifficulty(difficulty)
                    .stream().filter(q -> q != null)
                    .map(this::getQuestionDta).collect(Collectors.toList()),
                    HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<QuestionDto> addQuestion(Question question) {
        try {
            return new ResponseEntity<>(getQuestionDta(questionRepository.save(question)), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new QuestionDto(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<QuestionDto> updateQuestion(Question question) {
        try {
            return new ResponseEntity<>(getQuestionDta(questionRepository.save(question)), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new QuestionDto(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteQuestion(int id) {
        try {
            questionRepository.deleteById(id);
            if (!questionRepository.existsById(id))
                return new ResponseEntity<>("Deleted", HttpStatus.OK);

            return new ResponseEntity<>("Not Deleted", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }

    public QuestionDto getQuestionDta(Question question) {
        return new QuestionDto(
                question.getId(),
                question.getCategory(),
                question.getDifficulty(),
                question.getQuestion(),
                question.getOption1(),
                question.getOption2(),
                question.getOption3(),
                question.getOption4(),
                question.getRight_answer());
    }
}
