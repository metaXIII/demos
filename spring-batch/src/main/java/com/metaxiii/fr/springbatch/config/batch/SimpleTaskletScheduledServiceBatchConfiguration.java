package com.metaxiii.fr.springbatch.config.batch;

import com.metaxiii.fr.springbatch.service.PersonService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.PlatformTransactionManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
public class SimpleTaskletScheduledServiceBatchConfiguration {
  private static final Logger LOGGER = Logger.getLogger(SimpleTaskletScheduledServiceBatchConfiguration.class.getName());
  private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
  private final PersonService personService;
  private final JobRepository jobRepository;
  private final JobLauncher jobLauncher;
  private final PlatformTransactionManager transactionManager;

  public SimpleTaskletScheduledServiceBatchConfiguration(final PersonService personService, final JobRepository jobRepository, final JobLauncher jobLauncher, final PlatformTransactionManager transactionManager) {
    this.personService = personService;
    this.jobRepository = jobRepository;
    this.jobLauncher = jobLauncher;
    this.transactionManager = transactionManager;
  }


  @Scheduled(cron = "0/30 * * * * *")
  public void executerMonJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
    final var jobParameters = new JobParametersBuilder();
    jobLauncher.run(scheduleJob(), jobParameters.toJobParameters());
  }

  @Bean
  public Job scheduleJob() {
    return new JobBuilder("scheduleJob", jobRepository)
        .incrementer(new RunIdIncrementer())
        .start(step())
        .build();
  }

  private Step step() {
    return new StepBuilder("scheduleStep", jobRepository)
        .tasklet(tasklet(), transactionManager)
        .allowStartIfComplete(true)
        .build();
  }

  private Tasklet tasklet() {
    return (contribution, chunkContext) -> {
      if (LOGGER.isLoggable(Level.INFO)) {
        LOGGER.info("Simple Scheduled Tasklet : Executing tasklet in Simple Tasklet Scheduled : " + simpleDateFormat.format(new Date()));
      }
      personService.doSomething();
      return RepeatStatus.FINISHED;
    };
  }
}
