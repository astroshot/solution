package com.solution.classdecode.type;

/**
 * Created by Altair on 2017/3/30.
 */
public class ConstantIntegerInfo extends AbstractConstantInfo {

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String toString() {
        return String.format("%s\ntag: %d\nvalue: %d\n", this.getClass().getName(), tag, value);
    }
}
