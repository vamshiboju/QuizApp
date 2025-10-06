package com.example.QuizApp.service;

import com.example.QuizApp.dao.QuestionDao;
import com.example.QuizApp.dao.QuizDao;
import com.example.QuizApp.model.Question;
import com.example.QuizApp.model.QuestionWrapper;
import com.example.QuizApp.model.Quiz;
import com.example.QuizApp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionDao.finRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for (Question q : questionFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }
        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);

    }

    public ResponseEntity<Integer> getNoOfCorrectAnswers(Integer id, List<Response> responses) {
        Quiz quiz=quizDao.findById(id).get();
        List<Question> questions=quiz.getQuestions();
        int i=0;
        int result=0;
        for( Response r: responses){
            if(r.getAns().equals(questions.get(i).getRightAnswer())){
                result+=1;
            }
            i+=1;
        }
        return new ResponseEntity<>(result,HttpStatus.OK);

    }
}