package com.example.QuestionMicroService.Service;

import com.example.QuestionMicroService.Model.QuestionWrapper;
import com.example.QuestionMicroService.Model.Questions;
import com.example.QuestionMicroService.Model.Response;
import com.example.QuestionMicroService.Repo.QuestionDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionDao repo;

    public ResponseEntity<List<Questions>> getallquestions() {
        try {
            return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.getStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public List<Questions> getquestionByCategory(String category) {
        return repo.findBycategory(category);
    }

    public ResponseEntity<String> addQuestion(Questions question) {
        try{
            repo.saveAndFlush(question);
            return new ResponseEntity<>("SUCCESS",HttpStatus.CREATED);
        }
        catch (Exception e){
            e.getStackTrace();
        }
        return new ResponseEntity<>("BAD REQUEST" , HttpStatus.BAD_REQUEST);
    }

    public String deleteQue(int id) {
        repo.deleteById(id);
        return "DELETED";
    }

    public String updateQue(Questions questions) {
        repo.save(questions);
        return "UPDATED";
    }


    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, int numQue) {
        List<Integer> que = repo.FindRandomQuestionsByCategory(categoryName,numQue);

        return new ResponseEntity<>(que,HttpStatus.OK);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(List<Integer> questionsId) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Questions> ques = new ArrayList<>();

        for(Integer id : questionsId){
            ques.add(repo.findById(id).get());
        }

        for(Questions questions : ques){
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setSlno(questions.getSlno());
            wrapper.setQuestion(questions.getQuestion());
            wrapper.setOption1(questions.getOption1());
            wrapper.setOption2(questions.getOption2());
            wrapper.setOption3(questions.getOption3());
            wrapper.setOption4(questions.getOption4());

            wrappers.add(wrapper);
        }
        return new ResponseEntity<>(wrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int right = 0;
        for(Response response : responses) {
            Questions question = repo.findById(response.getSlno()).get();
            if(response.getResponse().equals(question.getCorrect_ans())) {
                right++;
            }
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
