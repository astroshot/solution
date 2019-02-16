package com.solution.classdecode.type;

/**
 * Created by Altair on 2017/3/30.
 */
public class ConstantMethodHandleInfo extends AbstractConstantInfo {

    private int referenceKind;
    private int referenceIndex;

    public int getReferenceKind() {
        return referenceKind;
    }

    public void setReferenceKind(int referenceKind) {
        this.referenceKind = referenceKind;
    }

    public int getReferenceIndex() {
        return referenceIndex;
    }

    public void setReferenceIndex(int referenceIndex) {
        this.referenceIndex = referenceIndex;
    }

    public String toString() {
        return String.format("%s\ntag: %d\nreferenceKind: %d\nreferenceIndex: %d\n",
                this.getClass().getName(), tag, referenceKind, referenceIndex);
    }
}
