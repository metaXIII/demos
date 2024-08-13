package com.metaxiii.fr.springbatch.config.batch;

import com.metaxiii.fr.springbatch.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SimpleScheduledServiceBatchConfigurationTest {

  @InjectMocks
  private SimpleScheduledServiceBatchConfiguration configuration;

  @Mock
  private PersonService personService;

  @Test
  void itShouldReportCurrentTime() {
    configuration.reportCurrentTime();
    verify(personService).doSomething();
  }
}
