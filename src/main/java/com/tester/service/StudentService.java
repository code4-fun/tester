package com.tester.service;

import com.tester.domain.Student;
import com.tester.repository.StudentRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
  @NonNull
  private final StudentRepository studentRepository;

  /**
   * Updates Student object.
   */
  public Student updateStudent(Student student) {
    Student studentToUpdate = getStudentById(student.getId());
    studentToUpdate.setName(student.getName());
    studentToUpdate.setDateOfBirth(student.getDateOfBirth());
    studentToUpdate.setUser(student.getUser());
    return createStudent(student);
  }

  /**
   * Creates Student object.
   */
  public Student createStudent(Student student) {
    return studentRepository.save(student);
  }

  /**
   * Deletes the entity with the given id.
   */
  public void deleteStudent(Long id) {
    studentRepository.deleteById(id);
  }

  /**
   * Retrieves Student object by its id.
   */
  public Student getStudentById(Long id) {
    return studentRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("There is no student with id=" + id));
  }

  /**
   * Returns all instances of Student class.
   */
  public List<Student> getAllStudents() {
    return studentRepository.findAll();
  }

  /**
   * Deletes all instances of Student class.
   */
  public void deleteAllStudents(){
    studentRepository.deleteAll();
  }
}