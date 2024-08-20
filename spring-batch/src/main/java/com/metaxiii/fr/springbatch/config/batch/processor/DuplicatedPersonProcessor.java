package com.metaxiii.fr.springbatch.config.batch.processor;

import com.metaxiii.fr.springbatch.entity.DuplicatedEntity;
import com.metaxiii.fr.springbatch.entity.PersonEntity;
import java.util.logging.Logger;
import org.springframework.batch.item.ItemProcessor;

public class DuplicatedPersonProcessor implements ItemProcessor<PersonEntity, DuplicatedEntity> {

  private static final Logger LOGGER = Logger.getLogger(DuplicatedPersonProcessor.class.getName());

  @Override
  public DuplicatedEntity process(final PersonEntity person) {
    LOGGER.info("Converting person and adding is active to true");
    final var entity = new DuplicatedEntity();
    entity.setId(person.getId());
    entity.setFirstName(person.getFirstName().toUpperCase());
    entity.setLastName(person.getLastName().toUpperCase());
    entity.setActive(true);
    return entity;
  }
}
