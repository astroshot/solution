package com.solution.classdecode.type;

/**
 * Created by Altair on 2017/3/30.
 */
public class ConstantFieldRefInfo extends AbstractConstantInfo {

    private int indexToClassInfo;
    private int indexToNameType;

    public int getIndexToClassInfo() {
        return indexToClassInfo;
    }

    public void setIndexToClassInfo(int indexToClassInfo) {
        this.indexToClassInfo = indexToClassInfo;
    }

    public int getIndexToNameType() {
        return indexToNameType;
    }

    public void setIndexToNameType(int indexToNameType) {
        this.indexToNameType = indexToNameType;
    }

    @Override
    public String toString() {
        return String.format("%s\ntag: %d\nindexToClassInfo: %d\nindexToNameType: %d\n",
                this.getClass().getName(), tag, indexToClassInfo, indexToNameType);
    }
}
