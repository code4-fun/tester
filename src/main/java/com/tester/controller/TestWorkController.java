package com.tester.controller;

import com.tester.domain.TestWork;
import com.tester.service.TestWorkService;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("testWorks")
@RequiredArgsConstructor
public class TestWorkController {
  @NonNull
  TestWorkService testWorkService;

  @GetMapping
  public List<TestWork> getAllTestWorks(){
    return testWorkService.getAllTestWorks();
  }

  @GetMapping("{id}")
  public TestWork getTestWork(@PathVariable Long id){
    return testWorkService.getTestWorkById(id);
  }

  @PostMapping
  public TestWork createTestWork(@RequestBody TestWork testWork){
    return testWorkService.createTestWork(testWork);
  }

  @PutMapping()
  public TestWork updateTestWork(@RequestBody TestWork testWork){
    return testWorkService.updateTestWork(testWork);
  }

  @DeleteMapping("{id}")
  public void deleteTestWork(@PathVariable Long id){
    testWorkService.deleteTestWork(id);
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(EntityNotFoundException.class)
  public String return400(EntityNotFoundException ex) {
    return ex.getMessage();
  }
}