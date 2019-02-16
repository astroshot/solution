package com.solution.classdecode.info;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * Created by dell on 2017/3/31.
 */
public class MethodAccessInfo {
    private static final int PUBLIC = 0x0001;
    private static final int PRIVATE = 0x0002;
    private static final int PROTECTED = 0x0004;
    private static final int STATIC = 0x0008;
    private static final int FINAL = 0x0010;
    private static final int SYNCHRONIZED = 0x0020;
    private static final int BRIDGE = 0x0040;
    private static final int VARARGS = 0x0080;
    private static final int NATIVE = 0x0100;
    private static final int ABSTRACT = 0x0400;
    private static final int STRICT = 0x0800;
    private static final int SYNTHETIC = 0x1000;

    private final int METHOD_ACCESS_NUM = 12;
    private final HashMap<Integer, String> maiMap;

    public MethodAccessInfo() {
        maiMap = new HashMap<Integer, String>(METHOD_ACCESS_NUM);

        maiMap.put(PUBLIC, "public");
        maiMap.put(PRIVATE, "private");
        maiMap.put(PROTECTED, "protected");
        maiMap.put(STATIC, "static");
        maiMap.put(FINAL, "final");
        maiMap.put(SYNCHRONIZED, "synchronized");
        maiMap.put(BRIDGE, "bridge");
        maiMap.put(VARARGS, "varargs");
        maiMap.put(NATIVE, "native");
        maiMap.put(ABSTRACT, "abstract");
        maiMap.put(STRICT, "strict");
        maiMap.put(SYNTHETIC, "synthetic");
    }

    @NotNull
    public static String getType(int access) {
        StringBuilder sb = new StringBuilder();

        if ((access & PUBLIC) == PUBLIC)
            sb.append("public ");
        else if ((access & PRIVATE) == PRIVATE)
            sb.append("private ");
        else if ((access & PROTECTED) == PROTECTED)
            sb.append("protected ");
        else
            sb.append("default ");

        if ((access & STATIC) == STATIC)
            sb.append("static ");

        if ((access & FINAL) == FINAL)
            sb.append("final ");

        if ((access & ABSTRACT) == ABSTRACT)
            sb.append("abstract ");

        if ((access & SYNCHRONIZED) == SYNCHRONIZED)
            sb.append("synchronized ");

        if ((access & BRIDGE) == BRIDGE)
            sb.append("bridge ");

        if ((access & VARARGS) == VARARGS)
            sb.append("varargs ");

        if ((access & NATIVE) == NATIVE)
            sb.append("native ");

        if ((access & STRICT) == STRICT)
            sb.append("strict ");

        if ((access & SYNTHETIC) == SYNTHETIC)
            sb.append("synthetic ");

        return sb.toString();
    }
}
