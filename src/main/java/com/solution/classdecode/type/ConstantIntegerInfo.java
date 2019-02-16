package com.solution.classdecode.type;

/**
 * Created by Altair on 2017/3/30.
 */
public class ConstantIntegerInfo {
    private int tag;
    private int value;

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

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
