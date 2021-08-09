package com.tester.controller;

import com.tester.domain.Question;
import com.tester.service.QuestionService;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testWorks/{id}/questions")
@RequiredArgsConstructor
public class QuestionController {
  @NonNull
  QuestionService questionService;

  @GetMapping
  public List<Question> getQuestions(@PathVariable("id") Long id){
    return questionService.getAllQuestionsOfTestWork(id);
  }
}