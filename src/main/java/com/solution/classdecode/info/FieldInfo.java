package com.solution.classdecode.info;

import com.solution.classdecode.type.ConstantUTF8Info;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by dell on 2017/3/31.
 */
public class FieldInfo {
    /**
     * access flags
     */
    private int accessFlags;
    /**
     * index in constant pool
     */
    private int nameIndex;
    private int descriptorIndex;
    /**
     * total num of attributes
     */
    private int attributesCount;
    private ArrayList<AttributeInfo> attributes;

    public FieldInfo() {
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public void setAccessFlags(int accessFlags) {
        this.accessFlags = accessFlags;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(int descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    public int getAttributesCount() {
        return attributesCount;
    }

    public void setAttributesCount(int attributesCount) {
        this.attributesCount = attributesCount;
    }

    public ArrayList<AttributeInfo> getAttributes() {
        return attributes;
    }

    public void allocateAttributes() {
        attributes = new ArrayList<AttributeInfo>(attributesCount);
    }

    public void read(DataInputStream in) throws IOException {
        accessFlags = in.readUnsignedShort();
        nameIndex = in.readUnsignedShort();
        descriptorIndex = in.readUnsignedShort();
        attributesCount = in.readUnsignedShort();
        if (attributesCount > 0) {
            AttributeInfo tmp = new AttributeInfo();
            allocateAttributes();
            for (int i = 0; i < attributesCount; i++) {
                tmp.read(in);
                attributes.add(i, tmp);
            }
        } else {
            System.out.println("This field contains no attribute.");
        }
    }

    public String getFlags() {
        return FieldAccessInfo.getType(accessFlags);
    }

    public String getFieldName(ClassFileInfo classFileInfo) {
        ConstantUTF8Info utf8Info = (ConstantUTF8Info) classFileInfo.get(nameIndex);
        return utf8Info.getContent();
    }

    public String getFieldDescriptor(ClassFileInfo classFileInfo) {
        ConstantUTF8Info utf8Info = (ConstantUTF8Info) classFileInfo.get(descriptorIndex);
        return utf8Info.getContent();
    }

    @Override
    public String toString() {
        return String.format("Field access: %s Name index: %d Descriptor index: %d",
                getFlags(), nameIndex, descriptorIndex);
    }

    public String toString(ClassFileInfo classFileInfo) {
        return String.format("Field access: %s Name: %s Descriptor: %s",
                getFlags(), getFieldName(classFileInfo), getFieldDescriptor(classFileInfo));
    }

    public String getAttributesInfo(ClassFileInfo classFileInfo) {
        StringBuilder sb = new StringBuilder();
        for (AttributeInfo info : attributes) {
            sb.append(info.getAttributeName(classFileInfo));
            sb.append(" with length ");
            sb.append(info.getLength());
            sb.append(" and values ");
            sb.append(info.getInfo());
        }
        return sb.toString();
    }
}
