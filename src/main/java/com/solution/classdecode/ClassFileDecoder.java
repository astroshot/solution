package com.solution.classdecode;

import com.solution.classdecode.exception.ClassFileReadingException;
import com.solution.classdecode.info.*;
import com.solution.classdecode.type.*;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Altair on 2017/3/28.
 */
public class ClassFileDecoder {
    private String fileName;
    private DataInputStream in;
    private ClassFileInfo classFileInfo;
//  private FieldAccessInfo fieldAccessInfo = new FieldAccessInfo();

    public ClassFileDecoder() {

    }

    public ClassFileDecoder(String fileName) {
        this.fileName = fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Symplify System.out.println(Object obj)
     *
     * @param obj
     */
    private void println(Object obj) {
        System.out.println(obj);
    }

    private void print(Object obj) {
        System.out.print(obj);
    }

    /**
     * decoding java class file
     */
    public boolean decode() {
        int constantTag;
        try {
            in = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName)));
            classFileInfo = new ClassFileInfo();
            classFileInfo.setMagic(in.readInt());

            if (classFileInfo.getMagic() != 0xCAFEBABE) {
                println("Illegal class file!");
            }

            println("Legal class file!");
            classFileInfo.setMinorVersion(in.readUnsignedShort());
            classFileInfo.setMajorVersion(in.readUnsignedShort());
            println(String.format("minorVersion: %d\nmajorVersion: %d",
                    classFileInfo.getMinorVersion(), classFileInfo.getMajorVersion()));

            classFileInfo.setConstantPoolCount(in.readUnsignedShort());
            println(String.format("Total constant pool num: %d\n", classFileInfo.getConstantPoolCount()));

            // Loading Constant pool
            for (int i = 1; i < classFileInfo.getConstantPoolCount(); i++) {
                println(String.format("Reading Constant pool info Num #%d:", i));
                constantTag = in.readUnsignedByte();
                switch (constantTag) {
                    case 1: // CONSTANT_Utf8_info
                        ConstantUTF8Info utf8Info = new ConstantUTF8Info();
                        utf8Info.setTag(constantTag);
                        utf8Info.setLength(in.readUnsignedShort());
                        byte[] bytes = new byte[utf8Info.getLength()];
                        for (int j = 0; j < utf8Info.getLength(); j++) {
                            bytes[j] = in.readByte();
                        }
                        utf8Info.setContent(bytes);
                        classFileInfo.put(i, utf8Info);

                        println(utf8Info);
                        break;

                    case 3: // CONSTANT_Integer_info
                        ConstantIntegerInfo intInfo = new ConstantIntegerInfo();
                        intInfo.setTag(constantTag);
                        intInfo.setValue(in.readInt());
                        classFileInfo.put(i, intInfo);

                        println(intInfo);
                        break;

                    case 4: // CONSTANT_Float_info
                        ConstantFloatInfo floatInfo = new ConstantFloatInfo();
                        floatInfo.setTag(constantTag);
                        floatInfo.setValue(in.readFloat());
                        classFileInfo.put(i, floatInfo);

                        println(floatInfo);
                        break;

                    case 5: // CONSTANT_Long_info
                        ConstantLongInfo longInfo = new ConstantLongInfo();
                        longInfo.setTag(constantTag);
                        longInfo.setValue(in.readLong());
                        classFileInfo.put(i, longInfo);
                        i++;

                        println(longInfo);
                        break;

                    case 6: // CONSTANT_Double_info
                        ConstantDoubleInfo doubleInfo = new ConstantDoubleInfo();
                        doubleInfo.setTag(constantTag);
                        doubleInfo.setValue(in.readDouble());
                        classFileInfo.put(i, doubleInfo);
                        i++;

                        println(doubleInfo);
                        break;

                    case 7: // CONSTANT_CLASS_info
                        ConstantClassInfo classInfo = new ConstantClassInfo();
                        classInfo.setTag(constantTag);
                        classInfo.setIndex(in.readUnsignedShort());
                        classFileInfo.put(i, classInfo);

                        println(classInfo);
                        break;

                    case 8: // CONSTANT_String_info
                        ConstantStringInfo stringInfo = new ConstantStringInfo();
                        stringInfo.setTag(constantTag);
                        stringInfo.setIndex(in.readUnsignedShort());
                        classFileInfo.put(i, stringInfo);

                        println(stringInfo);
                        break;

                    case 9: // CONSTANT_Fieldref_info
                        ConstantFieldRefInfo fieldRefInfo = new ConstantFieldRefInfo();
                        fieldRefInfo.setTag(constantTag);
                        fieldRefInfo.setIndexToClassInfo(in.readUnsignedShort());
                        fieldRefInfo.setIndexToNameType(in.readUnsignedShort());
                        classFileInfo.put(i, fieldRefInfo);

                        println(fieldRefInfo);
                        break;

                    case 10: // CONSTANT_Methodref_info
                        ConstantMethodRefInfo methodRefInfo = new ConstantMethodRefInfo();
                        methodRefInfo.setTag(constantTag);
                        methodRefInfo.setIndexToClassInfo(in.readUnsignedShort());
                        methodRefInfo.setIndexToNameType(in.readUnsignedShort());
                        classFileInfo.put(i, methodRefInfo);

                        println(methodRefInfo);
                        break;

                    case 11: // CONSTANT_InterfaceMethodref_info
                        ConstantInterfaceMethodRefInfo interfaceMethodRefInfo = new ConstantInterfaceMethodRefInfo();
                        interfaceMethodRefInfo.setTag(constantTag);
                        interfaceMethodRefInfo.setIndexToClassInfo(in.readUnsignedShort());
                        interfaceMethodRefInfo.setIndexToNameType(in.readUnsignedShort());
                        classFileInfo.put(i, interfaceMethodRefInfo);

                        println(interfaceMethodRefInfo);
                        break;

                    case 12: // CONSTANT_NameAndType_info
                        ConstantNameAndTypeInfo nameAndTypeInfo = new ConstantNameAndTypeInfo();
                        nameAndTypeInfo.setTag(constantTag);
                        nameAndTypeInfo.setIndexToClassInfo(in.readUnsignedShort());
                        nameAndTypeInfo.setIndexToNameType(in.readUnsignedShort());
                        classFileInfo.put(i, nameAndTypeInfo);

                        println(nameAndTypeInfo);
                        break;

                    case 15: // CONSTANT_MethodHandle_info
                        ConstantMethodHandleInfo methodHandleInfo = new ConstantMethodHandleInfo();
                        methodHandleInfo.setTag(constantTag);
                        methodHandleInfo.setReferenceKind(in.readUnsignedByte());
                        methodHandleInfo.setReferenceIndex(in.readUnsignedShort());
                        classFileInfo.put(i, methodHandleInfo);

                        println(methodHandleInfo);
                        break;

                    case 16: // CONSTANT_MethodType_info
                        ConstantMethodTypeInfo methodTypeInfo = new ConstantMethodTypeInfo();
                        methodTypeInfo.setTag(constantTag);
                        methodTypeInfo.setDescriptorIndex(in.readUnsignedShort());
                        classFileInfo.put(i, methodTypeInfo);

                        println(methodTypeInfo);
                        break;

                    case 18: // CONSTANT_InvokeDynamic_info
                        ConstantInvokeDynamicInfo invokeDynamicInfo = new ConstantInvokeDynamicInfo();
                        invokeDynamicInfo.setTag(constantTag);
                        invokeDynamicInfo.setBootstrapMethodAttrIndex(in.readUnsignedShort());
                        invokeDynamicInfo.setNameAndTypeIndex(in.readUnsignedShort());
                        classFileInfo.put(i, invokeDynamicInfo);

                        println(invokeDynamicInfo);
                        break;

                    default:
                        println(String.format("Tag is %d. Something is wrong!\n", constantTag));
                        throw new ClassFileReadingException("Illegal tag!");
                        //break;
                }
            } // Constant pool loading finished!

            // Now loading class access flags
            classFileInfo.setAccessFlags(in.readUnsignedShort());
            println(String.format("Class access: %s", FieldAccessInfo.getType(classFileInfo.getAccessFlags())));


            classFileInfo.setThisClass(in.readUnsignedShort());
            println(String.format("Class name: %s", classFileInfo.getClassName()));

            classFileInfo.setSuperClass(in.readUnsignedShort());
            println(String.format("Super class name: %s", classFileInfo.getSuperClassName()));
            if (classFileInfo.getSuperClass() == 0) {
                println("The super class is Object.");
            }

            // loading interfaces
            classFileInfo.setInterfacesCount(in.readUnsignedShort());
            if (classFileInfo.getInterfacesCount() > 0) {
                print("Interfaces:");
                int tmpInterface;
                classFileInfo.allocateInterfaces();
                for (int j = 0; j < classFileInfo.getInterfacesCount(); j++) {
                    tmpInterface = in.readUnsignedShort();
                    classFileInfo.getInterfaces().add(j, tmpInterface);
                }
                println(classFileInfo.getInterfacesNames());
                println("Interface decoding finished.\n");
            } else {
                println("Interface num is " + classFileInfo.getInterfacesCount());
            }

            // loading fields
            classFileInfo.setFieldsCount(in.readUnsignedShort());
            if (classFileInfo.getFieldsCount() > 0) {
                FieldInfo fieldInfo = new FieldInfo();
                classFileInfo.allocateFields();
                for (int j = 0; j < classFileInfo.getFieldsCount(); j++) {
                    fieldInfo.read(in);
                    classFileInfo.getFields().add(j, fieldInfo);
                    // show field info
                    println(fieldInfo.toString(classFileInfo));
                    println(fieldInfo.getAttributesInfo(classFileInfo));
                }
                println("Field decoding finished.\n");
            } else {
                println("Fields num is " + classFileInfo.getFieldsCount());
            }

            // loading methods
            classFileInfo.setMethodsCount(in.readUnsignedShort());
            if (classFileInfo.getMethodsCount() > 0) {
                MethodInfo methodInfo = new MethodInfo();
                classFileInfo.allocateMethods();
                for (int j = 0; j < classFileInfo.getMethodsCount(); j++) {
                    methodInfo.read(in);
                    classFileInfo.getMethods().add(j, methodInfo);
                    // show methods info
                    println(methodInfo.toString(classFileInfo));
                }
                println("Methods decoding finished.\n");
            } else {
                println("Methods num is " + classFileInfo.getMethodsCount());
            }

            // loading attributes
            classFileInfo.setAttributesCount(in.readUnsignedShort());
            if (classFileInfo.getAttributesCount() > 0) {
                AttributeInfo attributeInfo = new AttributeInfo();
                classFileInfo.allocateAttributes();
                for (int j = 0; j < classFileInfo.getAttributesCount(); j++) {
                    attributeInfo.read(in);
                    classFileInfo.getAttributes().add(j, attributeInfo);
                    // show attributes info
                    println(attributeInfo.toString(classFileInfo));
                }
                println("Attributes decoding finished.\n");
            } else {
                println("Attributes num is " + classFileInfo.getAttributesCount());
            }

            // Let's see if end of class file is reached.
            if (in.available() == 0) {
                println("End of class file: " + fileName);
            } else {
                println("Not End. Something is wrong?");
            }
            in.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
