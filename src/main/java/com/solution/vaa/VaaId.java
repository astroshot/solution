package com.solution.vaa;

import lombok.Data;

@Data
public class VaaId {

    private String formatISOIEC15459;

    private String formatISOIEC15418;

    private String urp;

    private byte[] binaryFmt;

    private String uri;

}
