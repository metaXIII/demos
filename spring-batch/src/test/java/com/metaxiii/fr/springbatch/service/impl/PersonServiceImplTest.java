package com.metaxiii.fr.springbatch.service.impl;

import com.metaxiii.fr.springbatch.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DataJpaTest
@ComponentScan(basePackages ="com.metaxiii.fr.springbatch.service")
class PersonServiceImplTest {

  @Autowired
  private PersonService personService;

  @Test
  void itShouldDoSomething() {
    assertDoesNotThrow(() -> personService.doSomething());
  }
}
