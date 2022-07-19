package com.solution.str;

public class Match {

    public static boolean match(String pattern, String text) {
        return match(pattern, 0, text, 0);
    }

    /**
     * 字符串匹配
     *
     * @param pattern ? 匹配一次，* 匹配多次
     * @param text    待匹配文本
     * @return 是否匹配
     */
    public static boolean match(String pattern, int patternStart, String text, int textStart) {
        int i = patternStart;
        int j = textStart;

        if (i == pattern.length()) {
            // 模式串已空，如果此时匹配串也为空，返回 true，否则返回 false
            return (j == text.length());
        } else if (pattern.charAt(i) == '?') {
            if (j == text.length()) { // 此时匹配串为空，返回 false
                return false;
            }
            return match(pattern, i + 1, text, j + 1);
        } else if (pattern.charAt(i) == '*') {
            for (j = textStart; j < text.length(); j++) {
                boolean matched = match(pattern, i + 1, text, j);
                if (matched) {
                    return true;
                }
            }
            return false;
        } else {
            if (pattern.charAt(i) == text.charAt(j)) {
                return match(pattern, i + 1, text, j + 1);
            }
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(match("te*t", "te8888"));
        System.out.println(match("te?t", "test"));
        System.out.println(match("te?t", "te"));
        System.out.println(match("te*t", "te"));
        System.out.println(match("te*t", "te8888tt"));
    }
}
