package com.solution.classdecode;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestClassDecode {

    @Test
    public void testDecode() {
        String projectPath = System.getProperty("user.dir");
        // Change class file to your own
        String filePath = projectPath + "/src/test/java/com/solution/classdecode/Integer.class";
        ClassFileDecoder decoder = new ClassFileDecoder(filePath);
        boolean res = decoder.decode();
        Assert.assertEquals(true, res);
    }
}
