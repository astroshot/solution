package com.solution.classdecode.type;

/**
 * Created by Altair on 2017/3/30.
 */
public class ConstantDoubleInfo extends AbstractConstantInfo {

    private double value;

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
