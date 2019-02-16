package com.solution.classdecode.type;

/**
 * Created by Altair on 2017/3/30.
 */
public class ConstantStringInfo {
    private int tag;
    private int index;

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String toString() {
        return String.format("%s\ntag: %d\nindex: %d\n",
                this.getClass().getName(), tag, index);
    }
}
