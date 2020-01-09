package com.solution;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.PrintStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AbstractTestCase {

    protected PrintStream out = System.out;

}
