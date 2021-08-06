package com.tester.repository;

import com.tester.domain.StudentsAnswer;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsAnswerRepository extends CrudRepository<StudentsAnswer, Long> {
  List<StudentsAnswer> findAll();
}