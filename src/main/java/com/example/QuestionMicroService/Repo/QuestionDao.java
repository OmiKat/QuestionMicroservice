package com.example.QuestionMicroService.Repo;


import com.example.QuestionMicroService.Model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Questions, Integer> {


    List<Questions> findBycategory(String category);


    @Query(value = "SELECT q.slno FROM  questions q Where q.category=:category ORDER BY RANDOM() LIMIT :numQ",nativeQuery = true)
    List<Integer> FindRandomQuestionsByCategory(String category, int numQ);
}
