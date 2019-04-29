package com.solution.classdecode.type;

/**
 * Created by Altair on 2017/3/30.
 */
public class ConstantLongInfo extends AbstractConstantInfo {

    private long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public String toString() {
        return String.format("%s\ntag: %d\nvalue: %d\n", this.getClass().getName(), tag, value);
    }
}
