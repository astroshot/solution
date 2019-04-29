package com.solution.classdecode.info;

import com.solution.classdecode.type.ConstantClassInfo;
import com.solution.classdecode.type.ConstantUTF8Info;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * Created by dell on 2017/3/31.
 */
public class ClassFileInfo {
    private int magic; // must be 0xCAFEBABE
    private int minorVersion;
    private int majorVersion;

    /**
     * Total num of constant in constant pool
     */
    private int constantPoolCount;

    // contains all constant pool items
    private TreeMap<Integer, Object> constantPoolInfo;

    /**
     * Access of class
     * public private protected
     */
    private int accessFlags;

    /**
     * Index in constant pool
     */
    private int thisClass;

    /**
     * Index in constant pool
     */
    private int superClass;

    /**
     * Total num of interfaces count
     */
    private int interfacesCount;
    private ArrayList<Integer> interfaces;

    /**
     * Total num of fields count
     */
    private int fieldsCount;
    private ArrayList<FieldInfo> fields;

    /**
     * Total num of methods count
     */
    private int methodsCount;
    private ArrayList<MethodInfo> methods;

    /**
     * Total num of attributes count
     */
    private int attributesCount;
    private ArrayList<AttributeInfo> attributes;

    public ClassFileInfo() {
        constantPoolInfo = new TreeMap<Integer, Object>();
    }

    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }

    public int getConstantPoolCount() {
        return constantPoolCount;
    }

    public void setConstantPoolCount(int constantPoolCount) {
        this.constantPoolCount = constantPoolCount;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public void setAccessFlags(int accessFlags) {
        this.accessFlags = accessFlags;
    }

    public int getThisClass() {
        return thisClass;
    }

    public void setThisClass(int thisClass) {
        this.thisClass = thisClass;
    }

    public int getSuperClass() {
        return superClass;
    }

    public void setSuperClass(int superClass) {
        this.superClass = superClass;
    }

    public int getInterfacesCount() {
        return interfacesCount;
    }

    public void setInterfacesCount(int interfacesCount) {
        this.interfacesCount = interfacesCount;
    }

    public int getFieldsCount() {
        return fieldsCount;
    }

    public void setFieldsCount(int fieldsCount) {
        this.fieldsCount = fieldsCount;
    }

    public int getMethodsCount() {
        return methodsCount;
    }

    public void setMethodsCount(int methodsCount) {
        this.methodsCount = methodsCount;
    }

    public int getAttributesCount() {
        return attributesCount;
    }

    public void setAttributesCount(int attributesCount) {
        this.attributesCount = attributesCount;
    }

    public void put(Integer key, Object value) {
        constantPoolInfo.put(key, value);
    }

    public Object get(Integer key) {
        return constantPoolInfo.get(key);
    }

    public void allocateInterfaces() {
        interfaces = new ArrayList<Integer>(interfacesCount);
    }

    public ArrayList<Integer> getInterfaces() {
        return interfaces;
    }

    public void allocateFields() {
        fields = new ArrayList<FieldInfo>(fieldsCount);
    }

    public ArrayList<FieldInfo> getFields() {
        return fields;
    }

    public void allocateMethods() {
        methods = new ArrayList<MethodInfo>(methodsCount);
    }

    public ArrayList<MethodInfo> getMethods() {
        return methods;
    }

    public void allocateAttributes() {
        attributes = new ArrayList<AttributeInfo>(attributesCount);
    }

    public ArrayList<AttributeInfo> getAttributes() {
        return attributes;
    }

    /**
     * Get the name of this class
     *
     * @return String
     */
    public String getClassName() {
        ConstantClassInfo constantClassInfo = (ConstantClassInfo) get(thisClass);
        ConstantUTF8Info className = (ConstantUTF8Info) get(constantClassInfo.getIndex());
        return className.getContent();
    }

    /**
     * Get the name of super class
     *
     * @return
     */
    public String getSuperClassName() {
        ConstantClassInfo constantClassInfo = (ConstantClassInfo) this.get(superClass);
        ConstantUTF8Info className = (ConstantUTF8Info) this.get(constantClassInfo.getIndex());
        return className.getContent();
    }

    /**
     * Get the names of all interfaces
     *
     * @return
     */
    public String getInterfacesNames() {
        StringBuilder sb = new StringBuilder();
        Iterator<Integer> iter = interfaces.iterator();
        while (iter.hasNext()) {
            ConstantClassInfo constantClassInfo = (ConstantClassInfo) get(iter.next());
            ConstantUTF8Info className = (ConstantUTF8Info) this.get(constantClassInfo.getIndex());
            sb.append(className.getContent());
            sb.append(' ');
        }
        return sb.toString();
    }
}
