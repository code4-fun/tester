package com.tester.dto;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Data Transfer Object for RightAnswer.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RightAnswerDto {
  @CsvBindByPosition(position = 0)
  private String questionId;

  @CsvBindByPosition(position = 1)
  private String code;
}