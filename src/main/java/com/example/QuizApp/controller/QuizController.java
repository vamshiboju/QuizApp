package com.example.QuizApp.controller;

import com.example.QuizApp.model.QuestionWrapper;
import com.example.QuizApp.model.Response;
import com.example.QuizApp.service.QuizService;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
   @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String>  CreateQuiz(@RequestParam String category,@RequestParam int numQ,@RequestParam String title){
        return quizService.createQuiz(category,numQ,title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>>  getQuizQuestions(@PathVariable Integer id){
        return  quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> getNoOfCorrectAnswers(@PathVariable Integer id,@RequestBody List<Response> responses){
        return quizService.getNoOfCorrectAnswers(id, responses);
    }

}
