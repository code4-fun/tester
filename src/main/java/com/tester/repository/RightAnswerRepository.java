package com.tester.repository;

import com.tester.domain.RightAnswer;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RightAnswerRepository extends CrudRepository<RightAnswer, Long> {
  List<RightAnswer> findAll();
}