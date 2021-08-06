package com.tester.service;

import com.tester.domain.RightAnswer;
import com.tester.repository.RightAnswerRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RightAnswerService {
  @NonNull
  private final RightAnswerRepository rightAnswerRepository;

  /**
   * Updates RightAnswer object.
   */
  public RightAnswer updateRightAnswer(RightAnswer rightAnswer) {
    RightAnswer rightAnswerToUpdate = getRightAnswerById(rightAnswer.getId());
    rightAnswerToUpdate.setQuestion(rightAnswer.getQuestion());
    rightAnswerToUpdate.setCode(rightAnswer.getCode());
    return createRightAnswer(rightAnswerToUpdate);
  }

  /**
   * Creates RightAnswer object.
   */
  public RightAnswer createRightAnswer(RightAnswer rightAnswer) {
    return rightAnswerRepository.save(rightAnswer);
  }

  /**
   * Deletes the entity with the given id.
   */
  public void deleteRightAnswer(Long id) {
    rightAnswerRepository.deleteById(id);
  }

  /**
   * Retrieves RightAnswer object by its id.
   */
  public RightAnswer getRightAnswerById(Long id) {
    return rightAnswerRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("There is no right answer with id=" + id));
  }

  /**
   * Returns all instances of RightAnswer class.
   */
  public List<RightAnswer> getAllRightAnswers() {
    return rightAnswerRepository.findAll();
  }

  /**
   * Deletes all instances of RightAnswer class.
   */
  public void deleteAllRightAnswers(){
    rightAnswerRepository.deleteAll();
  }
}