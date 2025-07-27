package com.lokesh.Quiz_Application_Project.service;

import com.lokesh.Quiz_Application_Project.dto.QuestionWrapper;
import com.lokesh.Quiz_Application_Project.model.Question;
import com.lokesh.Quiz_Application_Project.model.Quiz;
import com.lokesh.Quiz_Application_Project.model.Response;
import com.lokesh.Quiz_Application_Project.repository.QuestionRepository;
import com.lokesh.Quiz_Application_Project.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        try {

            List<Question> questions = questionRepository.findRandomQuestionsByCategory(category, numQ);

            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);
            System.out.println(questions.size());
            System.out.println(quizRepository.save(quiz));

            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id) {

        try {

            Optional<Quiz> quiz = quizRepository.findById(id);
            List<Question> questionsFromDB = quiz.get().getQuestions();
            List<QuestionWrapper> questionsToUser = new ArrayList<>();

            for (Question q : questionsFromDB) {
                QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestion(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
                questionsToUser.add(qw);
            }

            return new ResponseEntity<>(questionsToUser, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Integer> calculateResults(Integer id, List<Response> responses) {

        try {

            Quiz quiz = quizRepository.findById(id).get();
            List<Question> questions = quiz.getQuestions();
            int right = 0;

            Map<Integer, String> correctAnswers = new HashMap<>();

            for (Question question : questions) {
                correctAnswers.put(question.getId(), question.getRight_answer());
            }

            for (Response response : responses) {
                String correct = correctAnswers.get(response.getId());
                if (correct != null && correct.equals(response.getResponse())) {
                    right++;
                }
            }

            return new ResponseEntity<>(right, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(0,HttpStatus.NOT_FOUND);
    }
}
