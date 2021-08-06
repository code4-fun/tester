package com.tester.service;

import com.tester.domain.AnswerValue;
import com.tester.repository.AnswerValueRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerValueService {
  @NonNull
  private final AnswerValueRepository answerValueRepository;

  /**
   * Updates AnswerValue object.
   */
  public AnswerValue updateAnswerValue(AnswerValue answerValue) {
    AnswerValue answerValueToUpdate = getAnswerValueById(answerValue.getId());
    answerValueToUpdate.setAnswerText(answerValue.getAnswerText());
    answerValueToUpdate.setCode(answerValue.getCode());
    answerValueToUpdate.setQuestion(answerValue.getQuestion());
    return createAnswerValue(answerValue);
  }

  /**
   * Creates AnswerValue object.
   */
  public AnswerValue createAnswerValue(AnswerValue answerValue) {
    return answerValueRepository.save(answerValue);
  }

  /**
   * Deletes the entity with the given id.
   */
  public void deleteAnswerValue(Long id) {
    answerValueRepository.deleteById(id);
  }

  /**
   * Retrieves AnswerValue object by its id.
   */
  public AnswerValue getAnswerValueById(Long id) {
    return answerValueRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("There is no answer with id=" + id));
  }

  /**
   * Returns all instances of AnswerValue class.
   */
  public List<AnswerValue> getAllAnswerValues() {
    return answerValueRepository.findAll();
  }

  /**
   * Deletes all instances of AnswerValue class.
   */
  public void deleteAllAnswerValues(){
    answerValueRepository.deleteAll();
  }
}