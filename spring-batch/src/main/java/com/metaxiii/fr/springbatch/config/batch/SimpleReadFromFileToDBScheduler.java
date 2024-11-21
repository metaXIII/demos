package com.metaxiii.fr.springbatch.config.batch;

import java.util.Date;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SimpleReadFromFileToDBScheduler {

  private final JobLauncher jobLauncher;
  private final Job importUserJob;

  public SimpleReadFromFileToDBScheduler(final JobLauncher jobLauncher, final Job importUserJob) {
    this.jobLauncher = jobLauncher;
    this.importUserJob = importUserJob;
  }

  @Scheduled(cron = "0/30 * * * * *")
  public void reportCurrentTime()
    throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
    jobLauncher.run(importUserJob, new JobParametersBuilder().addDate("timestamp", new Date()).toJobParameters());
  }
}
