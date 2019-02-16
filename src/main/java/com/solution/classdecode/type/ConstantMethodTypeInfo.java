package com.solution.classdecode.type;

/**
 * Created by Altair on 2017/3/30.
 */
public class ConstantMethodTypeInfo {
    private int tag;
    private int descriptorIndex;

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(int descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    public String toString() {
        return String.format("%s\ntag: %d\ndescriptorIndex: %d\n",
                this.getClass().getName(), tag, descriptorIndex);
    }
}
