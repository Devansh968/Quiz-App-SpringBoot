package com.personalproject.QuizApp.service;

//import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import com.personalproject.QuizApp.Dao.QuestionDao;
import com.personalproject.QuizApp.model.Question;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
        return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
        // findall method will give the list of question;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {  
          //Using response entity cuse we want to display data as well as status code  at the client side
          try{
       return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
          }
          catch(Exception e){
            e.printStackTrace();
          }
return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public String addQuestion(Question question) {
        questionDao.save(question);
        return "success";
    }
}
