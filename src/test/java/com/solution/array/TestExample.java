package com.solution.array;

import com.solution.AbstractTestCase;
import org.junit.Assert;
import org.junit.Test;


public class TestExample extends AbstractTestCase {

    @Test
    public void simpleTest() {
        Assert.assertEquals(2, 1 + 1);
    }

    @Test
    public void selfAddTest() {
        int i = 0;
        int j = ++i;
        i = 0;
        int k = i++;
        Assert.assertEquals(0, k);
        Assert.assertEquals(1, i);
        Assert.assertEquals(1, j);
    }

}
