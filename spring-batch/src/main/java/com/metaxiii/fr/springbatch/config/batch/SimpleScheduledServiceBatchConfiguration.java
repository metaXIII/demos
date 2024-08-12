package com.metaxiii.fr.springbatch.config.batch;

import com.metaxiii.fr.springbatch.service.PersonService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
public class SimpleScheduledServiceBatchConfiguration {
  private static final Logger LOGGER = Logger.getLogger(SimpleScheduledServiceBatchConfiguration.class.getName());
  private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
  private final PersonService personService;

  public SimpleScheduledServiceBatchConfiguration(final PersonService personService) {
    this.personService = personService;
  }

  @Scheduled(cron = "0/30 * * * * *")
  public void reportCurrentTime() {
    if (LOGGER.isLoggable(Level.INFO)) {
      LOGGER.info("Simple Job : The time is now %s".formatted(simpleDateFormat.format(new Date())));
    }
    personService.doSomething();
  }
}
