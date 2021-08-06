package com.tester.repository;

import com.tester.domain.TestWork;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestWorkRepository extends CrudRepository<TestWork, Long> {
  List<TestWork> findAll();
}