package com.tester.service;

import com.tester.domain.Mark;
import com.tester.domain.Question;
import com.tester.domain.RightAnswer;
import com.tester.domain.StudentsAnswer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScoreService {
  @NonNull
  private final RightAnswerService rightAnswerService;

  @NonNull
  private final StudentsAnswerService studentsAnswerService;

  @NonNull
  private final QuestionService questionService;

  @NonNull
  private final MarkService markService;

  @NonNull
  private final StudentService studentService;

  @NonNull
  private final TestWorkService testWorkService;

  /**
   * Populates the table mark with the students' scores. Test is considered passed
   * if student gave +75% of right answers.
   */
  public void score(){
    Map<Long, List<Long>> testWorkAndQuestionsMap = testWorkAndQuestionsMap();
    Map<Long, Map<Long, List<String>>> studentAnswersMap = studentAnswersMap();
    Map<Long, List<String>> rightAnswerMap = rightAnswersMap();
    markService.deleteAllMark();

    for(Map.Entry<Long, List<Long>> item : testWorkAndQuestionsMap.entrySet()){
      int numberOfQuestionsInTest = item.getValue().size();
      for(Map.Entry<Long, Map<Long, List<String>>> item2 : studentAnswersMap.entrySet()){
        int counterOfAnswers = 0;
        int counterOfRightAnswers = 0;
        for(Map.Entry<Long, List<String>> item3 : item2.getValue().entrySet()){
          if(item.getValue().contains(item3.getKey())){
            counterOfAnswers++;
            if(compareTwoCollectionsOfStrings(item3.getValue(), rightAnswerMap.get(item3.getKey()))){
              counterOfRightAnswers++;
            }
          }
        }
        if(counterOfAnswers != 0){
          markService.createMark(Mark.builder().student(studentService.getStudentById(item2.getKey()))
              .testWork(testWorkService.getTestWorkById(item.getKey())).isPassed(
                  (double) counterOfRightAnswers/numberOfQuestionsInTest >= 0.75
              ).build());
        }
      }
    }
  }

  /**
   * Compares two collections of Strings not without
   * taking into account the order of the elements.
   * @param list1
   * @param list2
   * @return
   */
  public boolean compareTwoCollectionsOfStrings(List<String> list1, List<String> list2){
    if(list1 != null && list2 != null){
      if(list1.size() != list2.size()){
        return false;
      }
      return list1.stream().sorted()
          .collect(Collectors.toList())
          .equals(list2.stream().sorted().collect(Collectors.toList()));
    }
    return false;
  }

  /**
   * Retrieves tests and related questions in the format:
   * 1 -> [1, 2, 3, 4]
   * 2 -> [5, 6, 7, 8]
   * ...
   * which means test with id=1 is related to the questions listed in square brackets.
   * @return
   */
  public Map<Long, List<Long>> testWorkAndQuestionsMap(){
    Map<Long, List<Long>> testWorkAndQuestionsMap = new HashMap<>();

    List<Question> allQuestions = questionService.getAllQuestions();

    for(Question item : allQuestions){
      testWorkAndQuestionsMap.putIfAbsent(item.getTestWork().getId(), new ArrayList<>());
      testWorkAndQuestionsMap.get(item.getTestWork().getId()).add(item.getId());
    }
    return testWorkAndQuestionsMap;
  }

  /**
   * Answers of the students in the format
   * 1 student:
   *   1 -> [C]
   *   2 -> [A, B, C]
   *   3 -> [B]
   *   4 -> [A]
   * 2 student:
   *   5 -> [A]
   *   6 -> [A]
   *   ...
   * which means student with id=1 answered questions with the ids 1, 2, 3 and 4
   * and gave the answers listed in square brackets.
   * @return
   */
  public Map<Long, Map<Long, List<String>>> studentAnswersMap(){
    Map<Long, Map<Long, List<String>>> studentAnswerMap = new HashMap<>();

    List<StudentsAnswer> studentAnswerList = studentsAnswerService.getAllStudentsAnswers();

    // Не повторяющиеся ID студентов, сдававших тесты.
    List<Long> students = studentAnswerList.stream().map(i -> i.getStudent().getId()).distinct()
        .collect(Collectors.toList());

    for(Long item : students){
      Map<Long, List<String>> studentAnswers = new HashMap<>();
      for(StudentsAnswer item2 : studentAnswerList){
        if(item.equals(item2.getStudent().getId())){
          studentAnswers.putIfAbsent(item2.getQuestion().getId(), new ArrayList<>());
          studentAnswers.get(item2.getQuestion().getId()).add(item2.getAnswerCode());

        }
      }
      studentAnswerMap.put(item, studentAnswers);
    }
    return studentAnswerMap;
  }

  /**
   * Right answers in format
   * 1 -> [C]
   * 2 -> [A, C]
   * 3 -> [B]
   * ...
   * which means question with id=1 has one right answer C, question
   * with id=2 has two right answers - A and C etc.
   * @return
   */
  public Map<Long, List<String>> rightAnswersMap(){
    Map<Long, List<String>> rightAnswerMap = new HashMap<>();

    List<RightAnswer> allRightAnswers = rightAnswerService.getAllRightAnswers();

    for(RightAnswer item : allRightAnswers){
      rightAnswerMap.putIfAbsent(item.getQuestion().getId(), new ArrayList<>());
      rightAnswerMap.get(item.getQuestion().getId()).add(item.getCode());
    }
    return rightAnswerMap;
  }
}