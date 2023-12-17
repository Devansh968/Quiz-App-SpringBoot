package com.personalproject.QuizApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.personalproject.QuizApp.model.Response;
//import com.personalproject.QuizApp.model.Question;
import com.personalproject.QuizApp.service.quizService;
import com.personalproject.QuizApp.wrapper.QuizWrapper;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class quizController {

    @Autowired
    quizService Quizservice;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ,@RequestParam String title){
        return Quizservice.createQuiz(category,numQ,title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuizWrapper>> getQuiz(@PathVariable Integer id){
       return Quizservice.getQUiz(id);
}

  @PostMapping("submit/{id}")
  public ResponseEntity<Integer> SubmitQUiz(@PathVariable int id, @RequestBody List<Response> responses){
     return Quizservice.calculateResult(id,responses);
  }
}
