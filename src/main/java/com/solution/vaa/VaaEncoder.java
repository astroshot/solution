package com.solution.vaa;

import java.nio.charset.StandardCharsets;

public  class VaaEncoder {

    private static final String ISOIEC_15459 = "%s%s%s%s/%s";

    private static final String ISOIEC_15418 = "(%s)%s%s%s%s/%s";

    private static final String URP = "URP://%s.%s.%s/%s";

    private static final String URI = "http://%s/%s.%s.%s/%s";

    public static VaaId makeId(VaaInfo info) {
        if (info == null) {
            throw new RuntimeException("info can not be null");
        }

        VaaId id = new VaaId();
        id.setFormatISOIEC15459(String.format(ISOIEC_15459,
                info.getOrgCode(), info.getNationCode(), info.getIndustryCode(), info.getEnterpriseCode(), info.getInternalEnterpriseCode()));
        id.setFormatISOIEC15418(String.format(ISOIEC_15418, "15N",
                info.getOrgCode(), info.getNationCode(), info.getIndustryCode(), info.getEnterpriseCode(), info.getInternalEnterpriseCode()));
        id.setBinaryFmt(id.getFormatISOIEC15459().getBytes(StandardCharsets.UTF_8));
        id.setUrp(String.format(URP,
                info.getNationCode(), info.getIndustryCode(), info.getEnterpriseCode(), info.getInternalEnterpriseCode()));
        id.setUri(String.format(URI,"example.com",
                info.getNationCode(), info.getIndustryCode(), info.getEnterpriseCode(), info.getInternalEnterpriseCode()));
        return id;
    }


}
