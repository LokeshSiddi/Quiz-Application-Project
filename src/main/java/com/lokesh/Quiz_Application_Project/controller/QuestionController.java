package com.lokesh.Quiz_Application_Project.controller;

import com.lokesh.Quiz_Application_Project.dto.QuestionDto;
import com.lokesh.Quiz_Application_Project.model.Question;
import com.lokesh.Quiz_Application_Project.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@PreAuthorize("hasAnyRole('USER','ADMIN')")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/all")
    public ResponseEntity<List<QuestionDto>> getAllQuestion() {
        return questionService.getAllQuestion();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> getQuestionById(@PathVariable int id) {
        return questionService.getQuestionById(id);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<QuestionDto>> getQuestionByCategory(@PathVariable String category) {
        return questionService.getQuestionByCategory(category);
    }

    @GetMapping("/difficulty/{difficulty}")
    public ResponseEntity<List<QuestionDto>> getQuestionByDifficulty(@PathVariable String difficulty) {
        return questionService.getQuestionByDifficulty(difficulty);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<QuestionDto> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update")
    public ResponseEntity<QuestionDto> updateQuestion(@RequestBody Question question) {
        return questionService.updateQuestion(question);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int id) {
        return questionService.deleteQuestion(id);
    }


}
