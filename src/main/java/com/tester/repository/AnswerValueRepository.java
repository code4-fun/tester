package com.tester.repository;

import com.tester.domain.AnswerValue;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerValueRepository extends CrudRepository<AnswerValue, Long> {
  List<AnswerValue> findAll();
}