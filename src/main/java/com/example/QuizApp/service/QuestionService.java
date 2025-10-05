package com.example.QuizApp.service;

import com.example.QuizApp.Question;
import com.example.QuizApp.dao.QuestionDao;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questiondao;
    public List<Question> getAllQuestions(){
        return questiondao.findAll();
    }
    public List<Question> getQuestionsByCategory(String category){
        return questiondao.findByCategory(category);
    }
    public String addQuestion(Question question){
        questiondao.save(question);
        return "Success";
    }
}
