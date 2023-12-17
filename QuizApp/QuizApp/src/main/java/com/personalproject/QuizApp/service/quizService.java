package com.personalproject.QuizApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import javax.net.ssl.HttpsURLConnection;

//import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.personalproject.QuizApp.Dao.QuestionDao;
//import com.personalproject.QuizApp.Dao.QuestionDao;
import com.personalproject.QuizApp.Dao.QuizDao;
import com.personalproject.QuizApp.model.Question;
import com.personalproject.QuizApp.model.Quiz;
import com.personalproject.QuizApp.model.Response;
import com.personalproject.QuizApp.wrapper.QuizWrapper;

@Service
public class quizService {
    
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category,int numQ, String title ){
     List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
        Quiz quiz =  new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("success",HttpStatus.CREATED);
    }
     public ResponseEntity<List<QuizWrapper>> getQUiz(Integer id){
      Optional<Quiz> quiz =  quizDao.findById(id);
     List<Question> questionFromDB = quiz.get().getQuestions();

     List<QuizWrapper> questionForUsers = new ArrayList<>();
     for(Question q:questionFromDB){
      QuizWrapper qw = new QuizWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
      questionForUsers.add(qw);
     }

     return new ResponseEntity<>(questionForUsers,HttpStatus.OK);
    }
    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int right = 0;
        int i = 0;
        for(Response response : responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;

            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
