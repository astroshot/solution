package com.solution.classdecode;

import com.solution.AbstractTestCase;
import org.junit.Assert;
import org.junit.Test;


public class TestClassDecode extends AbstractTestCase {

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
