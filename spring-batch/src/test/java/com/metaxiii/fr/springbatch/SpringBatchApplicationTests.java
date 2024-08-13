package com.metaxiii.fr.springbatch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBatchApplicationTests {

  @Test
  void contextLoads() {
    Assertions.assertDoesNotThrow(() -> SpringBatchApplication.main(new String[] {}));
  }

}
