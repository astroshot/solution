package com.solution.str;

public class Match {

    /**
     * 字符串匹配
     *
     * @param pattern ? 匹配一次，* 匹配多次
     * @param text    待匹配文本
     * @return 是否匹配
     */
    public static boolean match(String pattern, String text) {
        int i = 0;
        int j = 0;

        if (pattern.charAt(i) == text.charAt(j) || pattern.charAt(i) == '?') {
            String subPattern = pattern.substring(i + 1, pattern.length() - 1);
            String subText = text.substring(j + 1, text.length() - 1);
            return match(subPattern, subText);
        } else if (pattern.charAt(i) == '*') {
            if (j == text.length()) {
                return true;
            }

            // TODO: not finished
            while (j < text.length()) {
                String subText = text.substring(j, text.length() - 1);
                if (!match(pattern, subText)) {
                    return false;
                }
                j++;
            }
            return false;
        } else if (i == pattern.length() - 1 && j == text.length() - 1) {
            return true;
        } else {
            return false;
        }

    }
}
