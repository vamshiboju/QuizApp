package com.example.QuizApp.controller;

import com.example.QuizApp.model.Question;
import com.example.QuizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    //To get all Questions
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    //To get questions based on category (Java / Python)
    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getCategoryQuestions(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }
    //to add questions in list
    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }
}
