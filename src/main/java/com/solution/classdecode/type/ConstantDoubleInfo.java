package com.solution.classdecode.type;

/**
 * Created by Altair on 2017/3/30.
 */
public class ConstantDoubleInfo {
    private int tag;
    private double value;

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("%s\ntag: %d\nvalue: %f\n", this.getClass().getName(), tag, value);
    }
}
