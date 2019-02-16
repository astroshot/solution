package com.solution.array;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestExample {

    @Test
    public void simpleTest() {
        Assert.assertEquals(2, 1 + 1);
    }
}
