package com.tester.service;

import com.opencsv.bean.CsvToBeanBuilder;
import com.tester.domain.Question;
import com.tester.domain.RightAnswer;
import com.tester.dto.RightAnswerDto;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TesterService {
  @NonNull
  private final RightAnswerService rightAnswerService;

  /**
   * Retrieves data from CSV file.
   *
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
   *
   * @param fileName absolute path to the CSV file
   * @throws IOException
   */
  @Transactional
  public void loadRightAnswers(String fileName) throws IOException {
    List<RightAnswer> rightAnswers = parseRightAnswers(fileName);
    if(rightAnswers != null){
      rightAnswerService.deleteAllRightAnswers();
      rightAnswers.forEach(rightAnswerService::createRightAnswer);
    }
  }

  /**
   * Parses list of RightAnswerDto objects and converts them
   * in the list of RightAnswer objects.
   * @param fileName CSV file with right answers
   * @return
   * @throws IOException
   */
  public List<RightAnswer> parseRightAnswers(String fileName) throws IOException {
    log.info("Start of processing of CSV file.");
    List<RightAnswerDto> rightAnswerDtos = processCSV(fileName);
    List<RightAnswer> rightAnswers;
    try{
      rightAnswers = rightAnswerDtos.stream().map(i -> RightAnswer
          .builder()
          .code(i.getCode())
          .question(Question.builder().id(Long.parseLong(i.getQuestionId())).build())
          .build()
      ).collect(Collectors.toList());
    } catch (Exception e){
      log.info("Wrong format of CSV file.");
      return null;
    }
    log.info("End of processing of CSV file. Processed {} rows.", rightAnswerDtos.size() );
    return rightAnswers;
  }
}