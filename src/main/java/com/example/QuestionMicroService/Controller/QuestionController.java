package com.example.QuestionMicroService.Controller;

import com.example.QuestionMicroService.Model.QuestionWrapper;
import com.example.QuestionMicroService.Model.Questions;
import com.example.QuestionMicroService.Model.Response;
import com.example.QuestionMicroService.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService service;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Questions>> getAllQuestions(){
        return service.getallquestions();
    }

    @GetMapping("category/{category}")
    public List<Questions> getQuestionByCategory(@PathVariable String category){
        return service.getquestionByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Questions question){
        return service.addQuestion(question);

    }

    @DeleteMapping("del/{id}")
    public String deleteQuestion(@PathVariable int id){
        return service.deleteQue(id);
    }

    @PutMapping("update")
    public String updateQuestion(@RequestBody Questions questions){
        return service.updateQue(questions);
    }

    @GetMapping("Generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName , @RequestParam int numQue){
        return service.getQuestionsForQuiz(categoryName,numQue);
    }

    @PostMapping("getques")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionsId){
        return service.getQuestionFromId(questionsId);
    }

    @PostMapping("getscore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return service.getScore(responses);
    }


}
