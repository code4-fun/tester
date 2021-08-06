package com.tester.repository;

import com.tester.domain.Question;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
  List<Question> findAll();
}