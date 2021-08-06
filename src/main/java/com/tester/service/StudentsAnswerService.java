package com.tester.service;

import com.tester.domain.StudentsAnswer;
import com.tester.repository.StudentsAnswerRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentsAnswerService {
  @NonNull
  private final StudentsAnswerRepository studentsAnswerRepository;

  /**
   * Updates StudentsAnswer object.
   */
  public StudentsAnswer updateStudentsAnswer(StudentsAnswer studentsAnswer) {
    StudentsAnswer studentsAnswerToUpdate = getStudentsAnswerId(studentsAnswer.getId());
    studentsAnswerToUpdate.setQuestion(studentsAnswer.getQuestion());
    studentsAnswerToUpdate.setAnswerCode(studentsAnswer.getAnswerCode());
    studentsAnswerToUpdate.setStudent(studentsAnswer.getStudent());
    return createStudentsAnswer(studentsAnswerToUpdate);
  }

  /**
   * Creates StudentsAnswer object.
   */
  public StudentsAnswer createStudentsAnswer(StudentsAnswer studentsAnswer) {
    return studentsAnswerRepository.save(studentsAnswer);
  }

  /**
   * Deletes the entity with the given id.
   */
  public void deleteStudentsAnswer(Long id) {
    studentsAnswerRepository.deleteById(id);
  }

  /**
   * Retrieves StudentsAnswer object by its id.
   */
  public StudentsAnswer getStudentsAnswerId(Long id) {
    return studentsAnswerRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("There is no students answer with id=" + id));
  }

  /**
   * Returns all instances of StudentsAnswer class.
   */
  public List<StudentsAnswer> getAllStudentsAnswers() {
    return studentsAnswerRepository.findAll();
  }

  /**
   * Deletes all instances of StudentsAnswer class.
   */
  public void deleteAllStudentsAnswers(){
    studentsAnswerRepository.deleteAll();
  }
}