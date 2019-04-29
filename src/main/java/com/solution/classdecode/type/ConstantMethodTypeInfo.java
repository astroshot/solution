package com.solution.classdecode.type;

/**
 * Created by Altair on 2017/3/30.
 */
public class ConstantMethodTypeInfo extends AbstractConstantInfo {

    private int descriptorIndex;

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
