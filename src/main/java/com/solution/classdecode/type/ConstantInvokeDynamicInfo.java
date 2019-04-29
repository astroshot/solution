package com.solution.classdecode.type;

/**
 * Created by Altair on 2017/3/30.
 */
public class ConstantInvokeDynamicInfo extends AbstractConstantInfo {

    private int bootstrapMethodAttrIndex;
    private int nameAndTypeIndex;

    public int getBootstrapMethodAttrIndex() {
        return bootstrapMethodAttrIndex;
    }

    public void setBootstrapMethodAttrIndex(int bootstrapMethodAttrIndex) {
        this.bootstrapMethodAttrIndex = bootstrapMethodAttrIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

    public void setNameAndTypeIndex(int nameAndTypeIndex) {
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public String toString() {
        return String.format("%s\ntag: %d\nbootstrapMethodAttrIndex: %d\nnameAndTypeIndex: %d\n",
                this.getClass().getName(), tag, bootstrapMethodAttrIndex, nameAndTypeIndex);
    }
}
