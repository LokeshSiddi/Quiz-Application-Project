package com.lokesh.Quiz_Application_Project.controller;


import com.lokesh.Quiz_Application_Project.dto.QuestionWrapper;
import com.lokesh.Quiz_Application_Project.model.Response;
import com.lokesh.Quiz_Application_Project.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestParam String category,@RequestParam int numQ,@RequestParam String title) {
        return quizService.createQuiz(category,numQ,title);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id) {
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int id, @RequestBody List<Response> responses) {
        return quizService.calculateResults(id, responses);
    }

}
