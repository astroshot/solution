package com.solution.classdecode.type;

/**
 * Created by Altair on 2017/3/30.
 */
public class ConstantFloatInfo {
    private int tag;
    private float value;

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String toString() {
        return String.format("%s\ntag: %d\nvalue: %f\n", this.getClass().getName(), tag, value);
    }
}
