package com.tester.service;

import com.tester.domain.TestWork;
import com.tester.repository.TestWorkRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestWorkService {
  @NonNull
  private final TestWorkRepository testWorkRepository;

  /**
   * Updates TestWork object.
   */
  public TestWork updateTestWork(TestWork testWork) {
    TestWork testWorkToUpdate = getTestWorkById(testWork.getId());
    testWorkToUpdate.setName(testWork.getName());
    testWorkToUpdate.setDate(testWork.getDate());
    return createTestWork(testWork);
  }

  /**
   * Creates TestWork object.
   */
  public TestWork createTestWork(TestWork testWork) {
    return testWorkRepository.save(testWork);
  }

  /**
   * Deletes the entity with the given id.
   */
  public void deleteTestWork(Long id) {
    testWorkRepository.deleteById(id);
  }

  /**
   * Retrieves TestWork object by its id.
   */
  public TestWork getTestWorkById(Long id) {
    return testWorkRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("There is no test work with id=" + id));
  }

  /**
   * Returns all instances of TestWork class.
   */
  public List<TestWork> getAllTestWorks() {
    return testWorkRepository.findAll();
  }

  /**
   * Deletes all instances of TestWork class.
   */
  public void deleteAllTestWorks(){
    testWorkRepository.deleteAll();
  }
}