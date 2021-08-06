package com.tester.repository;

import com.tester.domain.Mark;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkRepository extends CrudRepository<Mark, Long> {
  List<Mark> findAll();
}