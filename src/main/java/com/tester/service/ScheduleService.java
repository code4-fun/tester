package com.tester.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {
  @Value("${fileLocation}")
  private String fileLocation;

  @NonNull
  private final TesterService testerService;

  @NonNull
  private final ScoreService scoreService;

  /**
   * Carries out tasks by schedule.
   * @throws IOException
   */
  @Scheduled(cron = "${timeToLoad}")
  public void scheduledTasks() throws IOException {
    if(Files.exists(Paths.get(fileLocation))){
      testerService.loadRightAnswers(fileLocation);
    }
    scoreService.score();
  }
}