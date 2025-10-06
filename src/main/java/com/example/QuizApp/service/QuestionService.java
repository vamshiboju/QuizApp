package com.example.QuizApp.service;

import com.example.QuizApp.model.Question;
import com.example.QuizApp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questiondao;
    public ResponseEntity<List<Question>> getAllQuestions(){
        try {
            return new ResponseEntity<>(questiondao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<List<Question>> getQuestionsByCategory(String category){
        try {
            return new ResponseEntity<>(questiondao.findByCategory(category),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<String> addQuestion(Question question){
        try {
            questiondao.save(question);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(".Unsuccessful",HttpStatus.CONFLICT);
    }
}
