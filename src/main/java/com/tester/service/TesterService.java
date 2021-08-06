package com.tester.service;

import com.opencsv.bean.CsvToBeanBuilder;
import com.tester.domain.Question;
import com.tester.domain.RightAnswer;
import com.tester.dto.RightAnswerDto;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import javax.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TesterService {
  @NonNull
  private final RightAnswerService rightAnswerService;

  /**
   * Retrieves data from CSV file.
   * @param fileName absolute path to the CSV file
   * @return list of RightAnswerDto objects
   * @throws IOException
   */
  public List<RightAnswerDto> processCSV(String fileName) throws IOException {
    return new CsvToBeanBuilder(new FileReader(fileName))
        .withType(RightAnswerDto.class)
        .build()
        .parse();
  }

  /**
   * Creates RightAnswer objects based on data from CSV file
   * and uploads them to the data base.
   * @param fileName absolute path to the CSV file
   * @throws IOException
   */
  @Transactional
  public void loadRightAnswers(String fileName) throws IOException {
      rightAnswerService.deleteAllRightAnswers();
      processCSV(fileName).forEach(i -> rightAnswerService.createRightAnswer(
          RightAnswer
              .builder()
              .code(i.getCode())
              .question(Question.builder().id(Long.parseLong(i.getQuestionId())).build())
              .build()
          )
      );
  }

  public void score(){
  }
}