package com.tester.service;

import com.tester.domain.Question;
import com.tester.repository.QuestionRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {
  @NonNull
  private final QuestionRepository questionRepository;

  /**
   * Updates Question object.
   */
  public Question updateQuestion(Question question) {
    Question questionToUpdate = getQuestionById(question.getId());
    questionToUpdate.setQuestionText(question.getQuestionText());
    questionToUpdate.setTestWork(question.getTestWork());
    return createQuestion(questionToUpdate);
  }

  /**
   * Creates Question object.
   */
  public Question createQuestion(Question question) {
    return questionRepository.save(question);
  }

  /**
   * Deletes the entity with the given id.
   */
  public void deleteQuestion(Long id) {
    questionRepository.deleteById(id);
  }

  /**
   * Retrieves Question object by its id.
   */
  public Question getQuestionById(Long id) {
    return questionRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("There is no question with id=" + id));
  }

  /**
   * Returns all instances of Question class.
   */
  public List<Question> getAllQuestions() {
    return questionRepository.findAll();
  }

  /**
   * Deletes all instances of Question class.
   */
  public void deleteAllQuestions(){
    questionRepository.deleteAll();
  }
}