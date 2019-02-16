package com.solution.classdecode.type;

/**
 * Created by Altair on 2017/3/30.
 */
public class ConstantLongInfo {
    private int tag;
    private long value;

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

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
