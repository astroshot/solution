package com.solution.classdecode.exception;

import java.io.IOException;

/**
 * Created by dell on 2017/3/31.
 */
public class ClassFileReadingException extends IOException {
    public ClassFileReadingException(String msg) {
        super(msg);
    }
}
