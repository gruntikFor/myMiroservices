package com.gruntik.microservices;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@SpringBootTest
class MicroservicesApplicationTests {

    @Test
    void contextLoads() {
        Assertions.assertEquals(1L,1L);
    }

}
