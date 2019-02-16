package com.solution.classdecode.info;

import com.solution.classdecode.type.ConstantUTF8Info;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by dell on 2017/3/31.
 */
public class AttributeInfo {
    /**
     * u2 type
     * The constant pool entry at name_index must be CONSTANT_UTF8_info
     */
    private int nameIndex;
    /**
     * u4 type
     */
    private int length;
    /**
     * u1 type
     */
    private ArrayList<Integer> info;

    public AttributeInfo() {
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void read(DataInputStream in) throws IOException {
        nameIndex = in.readUnsignedShort();
        length = in.readInt();
        if (length > 0) {
            info = new ArrayList<Integer>(length);
            for (int i = 0; i < length; i++) {
                info.add(i, in.readUnsignedByte());
            }
        } else {
            System.out.println("Attributes count of attribute info is " + length);
        }
    }

    public String getAttributeName(ClassFileInfo classFileInfo) {
        ConstantUTF8Info utf8Info = (ConstantUTF8Info) classFileInfo.get(nameIndex);
        return utf8Info.getContent();
    }

    @Override
    public String toString() {
        return String.format("Attribute name index: %d length: %d",
                nameIndex, length);
    }

    public String toString(ClassFileInfo classFileInfo) {
        return String.format("Attribute name: %s length: %d",
                getAttributeName(classFileInfo), length);
    }

    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        for (Integer i : info) {
            sb.append(i);
            sb.append(' ');
        }
        return sb.toString();
    }
}
