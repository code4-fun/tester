package com.tester.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="question")
public class Question {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String questionText;

  @ManyToOne
  @JoinColumn(name = "test_work_id", referencedColumnName = "id")
  private TestWork testWork;
}