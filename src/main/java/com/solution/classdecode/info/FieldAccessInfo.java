package com.solution.classdecode.info;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * Created by dell on 2017/3/31.
 */
public class FieldAccessInfo {
    private static final int PUBLIC = 0x0001;
    private static final int PRIVATE = 0x0002;
    private static final int PROTECTED = 0x0004;
    private static final int STATIC = 0x0008;
    private static final int FINAL = 0x0010;
    private static final int VOLATILE = 0x0040;
    private static final int TRANSIENT = 0x0080;
    private static final int SYNTHETIC = 0x1000;
    private static final int ENUM = 0x4000;

    private static final int FIELD_ACCESS_NUM = 9;
    private final HashMap<Integer, String> fieldAccessMap;

    public FieldAccessInfo() {
        fieldAccessMap = new HashMap<Integer, String>(FIELD_ACCESS_NUM);

        // Field access and property flags
        fieldAccessMap.put(PUBLIC, "public");
        fieldAccessMap.put(PRIVATE, "private");
        fieldAccessMap.put(PROTECTED, "protected");
        fieldAccessMap.put(STATIC, "static");
        fieldAccessMap.put(FINAL, "final");
        fieldAccessMap.put(VOLATILE, "volatile");
        fieldAccessMap.put(TRANSIENT, "transient");
        fieldAccessMap.put(SYNTHETIC, "synthetic");
        fieldAccessMap.put(ENUM, "enum");
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

        if ((access & VOLATILE) == VOLATILE)
            sb.append("volatile ");
        else if ((access & TRANSIENT) == TRANSIENT)
            sb.append("transient ");

        if ((access & SYNTHETIC) == SYNTHETIC)
            sb.append("synthetic ");

        if ((access & ENUM) == ENUM)
            sb.append("enum ");

        return sb.toString();
    }
}
