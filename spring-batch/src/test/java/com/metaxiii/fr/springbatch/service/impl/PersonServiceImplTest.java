package com.metaxiii.fr.springbatch.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.metaxiii.fr.springbatch.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

@DataJpaTest
@ComponentScan(basePackages = "com.metaxiii.fr.springbatch.service")
class PersonServiceImplTest {

  @Autowired
  private PersonService personService;

  @Test
  void itShouldDoSomething() {
    assertDoesNotThrow(() -> personService.doSomething());
  }
}
