package com.tester.service;

import com.tester.domain.Mark;
import com.tester.repository.MarkRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarkService {

  @NonNull
  private final MarkRepository markRepository;

  /**
   * Updates Mark object.
   */
  public Mark updateMark(Mark mark) {
    Mark markToUpdate = getMarkById(mark.getId());
    markToUpdate.setStudent(mark.getStudent());
    markToUpdate.setTestWork(mark.getTestWork());
    markToUpdate.setPassed(mark.isPassed());
    return createMark(markToUpdate);
  }

  /**
   * Creates Mark object.
   */
  public Mark createMark(Mark mark) {
    return markRepository.save(mark);
  }

  /**
   * Deletes the entity with the given id.
   */
  public void deleteMark(Long id) {
    markRepository.deleteById(id);
  }

  /**
   * Retrieves Mark object by its id.
   */
  public Mark getMarkById(Long id) {
    return markRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("There is no mark with id=" + id));
  }

  /**
   * Returns all instances of Mark class.
   */
  public List<Mark> getAllMarks() {
    return markRepository.findAll();
  }

  /**
   * Deletes all instances of Mark class.
   */
  public void deleteAllMark(){
    markRepository.deleteAll();
  }
}