package com.metaxiii.fr.springbatch.service.impl;

import com.metaxiii.fr.springbatch.repository.PersonRepository;
import com.metaxiii.fr.springbatch.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PersonServiceImpl implements PersonService {
  private static final Logger logger = Logger.getLogger(PersonServiceImpl.class.getName());
  private final PersonRepository personRepository;

  public PersonServiceImpl(final PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @Override
  public void doSomething() {
    final var all = personRepository.findAll();
    if (logger.isLoggable(Level.INFO)) {
      logger.info("doSomething for : %d elements".formatted(all.size()));
    }
  }
}
