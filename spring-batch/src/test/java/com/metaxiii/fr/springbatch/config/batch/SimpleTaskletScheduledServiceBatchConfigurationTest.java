package com.metaxiii.fr.springbatch.config.batch;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import com.metaxiii.fr.springbatch.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringBatchTest
@Import(TestConfig.class)
@SpringJUnitConfig(value = SimpleTaskletScheduledServiceBatchConfiguration.class)
class SimpleTaskletScheduledServiceBatchConfigurationTest {

  @Autowired
  private JobLauncherTestUtils jobLauncherTestUtils;

  @Autowired
  private SimpleTaskletScheduledServiceBatchConfiguration batchConfiguration;

  @Autowired
  private JobRepositoryTestUtils jobRepositoryTestUtils;

  @MockBean
  private PersonService personService;

  @Test
  void itShouldExecuterMonJob() throws Exception {
    jobRepositoryTestUtils.removeJobExecutions();
    final var jobParameters = this.jobLauncherTestUtils.getUniqueJobParameters();
    final var jobExecution = this.jobLauncherTestUtils.launchJob(jobParameters);
    assertEquals("COMPLETED", jobExecution.getStatus().name());
    verify(personService).doSomething();
  }

  @Test
  void itShouldScheduleJob() {
    assertDoesNotThrow(() -> batchConfiguration.scheduleJob());
  }
}
