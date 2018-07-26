package array;

import java.util.HashSet;

/**
 * Created by dell on 2017/4/21.
 */
public class CharTest {
    public static void swap(char[] str, int i, int j) {
        char ch = str[i];
        str[i] = str[j];
        str[j] = ch;
    }

    private static void reverse(char[] str, int i, int j) {
        while (i < j) {
            swap(str, i, j);
            i++;
            j--;
        }
    }

    /**
     * @param str
     * @param k
     */
    public static void cyclicMove(char[] str, int k) {
        k = k % str.length;
        reverse(str, 0, str.length - 1 - k);
        reverse(str, str.length - k, str.length - 1);
        reverse(str, 0, str.length - 1);
    }

    public static void testCyclicMove() {
        String str = "abcdefg123456";
        int move = 5;
        char[] ch = str.toCharArray();
        cyclicMove(ch, move);
        System.out.println(new String(ch));
    }

    /**
     * delete the number in a string
     */
    public static String delNumber(String str) {
        char[] arr = str.toCharArray();
        //char[] res;
        int cnt = 0, i, j;

        // count the number of number and get the first index of number
//        for (i = 0; i < arr.length; i++) {
//            if (arr[i] >= '1' && arr[i] <= '9') {
//                cnt++;
//            }
//        }

        /*
        res = new char[arr.length - cnt];
        for (int i = 0, j = 0; j < arr.length; j++) {
            if (!(arr[j] >= '1' && arr[j] <= '9'))
                res[i++] = arr[j];
        }
        return new String(res);
        */
        i = 0;
        while (!isDigit(arr[i]))
            i++;
        j = i;
        while (isDigit(arr[j]))
            j++;
        while (j < arr.length) {
            if (!isDigit(arr[j]))
                arr[i++] = arr[j++];
            else
                j++;
        }
        while (i < arr.length)
            arr[i++] = '\0';
        return new String(arr);
    }

    public static void testDelNum() {
        String str = "0abc123de4fg56";
        System.out.println(delNumber(str));
    }

    /**
     * input: ab**cd**e*12
     * turns to: *****abcde12
     *
     * @param str
     * @return num of *
     */
    public static int moveStar(char[] str) {
        int i = str.length - 1, j = i;
        while (i >= 0 && j >= 0) {
            while (str[i] != '*')
                i--;
            j = i - 1;
            while (str[j] == '*')
                j--;
            swap(str, i--, j--);
        }
        return i + 1;
    }

    public static void testMoveStar() {
        String ss = "a**b***c**d**e*12";
        char[] str = ss.toCharArray();
        System.out.println(moveStar(str));
        System.out.println(new String(str));
    }

    public static boolean isDigit(char ch) {
        if (ch >= '0' && ch <= '9')
            return true;
        else
            return false;
    }

    /**
     * get longest increasing continuous digit sub string
     *
     * @param str input string, for example: ads12340567l456789ds34567aaa
     * @return the longest sub string, 456789
     */
    public static String longestIncreasingNumbers(String str) {
        char[] arr = str.toCharArray();
        char[] buffer;
        int maxLen = 1, len = 1, itmp = 0;
        for (int i = 1; i < arr.length; i++) {
            if (isDigit(arr[i - 1]) && isDigit(arr[i]) && arr[i] == arr[i - 1] + 1) {
                len++;
            } else {
                if (len > maxLen) {
                    maxLen = len;
                    itmp = i - maxLen;
                }
                len = 1;
            }
        }

        buffer = new char[maxLen];
        for (int i = itmp; i < itmp + maxLen; i++) {
            buffer[i - itmp] = arr[i];
        }
        return new String(buffer);
    }

    public static void testLongestIncreasingSubStr() {
        String str = "ads1230456789l45678ds345789aaa";
        System.out.println(longestIncreasingNumbers(str));
    }


    public static String zigZagString(String s, int nRow) {
        char[] arr = new char[s.length()];

        return new String(arr);
    }
}
