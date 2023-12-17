package com.personalproject.QuizApp.Dao;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;

//import com.personalproject.QuizApp.model.Question;
import com.personalproject.QuizApp.model.Quiz;


public interface QuizDao extends JpaRepository<Quiz,Integer> {
}
    
    
