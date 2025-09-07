package com.solution.vaa;

import lombok.Data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class VaaInfo {

    private static Pattern three = Pattern.compile("[0-9A-Z]{3}");

    private static Pattern moreThanOnce = Pattern.compile("[0-9A-Z]+");

    /**
     * *, +, -, ., /, (, ), !
     */
    private static Pattern internalEnterprisePattern = Pattern.compile("[0-9A-Za-z*+\\-/,()!]+");

    /**
     * 发码机构代码，长度 3 位
     */
    private String orgCode;

    /**
     * 国家编码, 3 位
     */
    private String nationCode;

    /**
     * 行业编码, 3 位
     */
    private String industryCode;

    /**
     * 企业编码, <= 20 位
     */
    private String enterpriseCode;

    /**
     * 企业内部编码 不定
     */
    private String internalEnterpriseCode;

    protected static boolean validThree(String text) {
        Matcher matcher;
        if (text == null || text.length() > 3) {
            return false;
        }

        matcher = three.matcher(text);
        if (!matcher.find()) {
            return false;
        }
        return true;
    }

    public boolean valid() {
        if (!validThree(this.orgCode)) {
            return false;
        }

        if (!validThree(this.nationCode)) {
            return false;
        }

        if (!validThree(this.industryCode)) {
            return false;
        }

        Matcher matcher;
        if (this.enterpriseCode == null || this.enterpriseCode.length() <= 0 || this.enterpriseCode.length() > 20) {
            return false;
        }
        matcher = moreThanOnce.matcher(this.enterpriseCode);
        if (!matcher.find()) {
            return false;
        }

        if (this.internalEnterpriseCode == null || this.internalEnterpriseCode.length() <= 0 ) {
            return false;
        }

        matcher = internalEnterprisePattern.matcher(this.internalEnterpriseCode);
        if (!matcher.find()) {
            return false;
        }

        return true;

    }
}
