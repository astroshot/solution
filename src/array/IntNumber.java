package array;

import sort.Sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

/**
 * Created by dell on 2017/4/21.
 */
public class IntNumber {
    public static int numberOfOne(int num) {
        int count = 0;
        do {
            num = num & (num - 1);
            count++;
        } while (num != 0);
        return count;
    }

    public static void testNumberOfOne() {
        System.out.println(numberOfOne(Integer.MIN_VALUE));
    }

    /**
     * input: N
     * output: 1, 2, 3, 4 ... 9999..9(N)
     *
     * @param N
     */
    public static void outputOneToN(int N) {
        int[] arr = new int[N];
        int count = pow10(N);
        for (int i = 0; i < count; i++) {
            outputNumArr(arr);
            increment(arr);
        }
    }

    /**
     * may overflow!
     *
     * @param N power set
     * @return the N power of 10
     */
    private static int pow10(int N) {
        int tmp = 1;
        for (int i = 0; i < N; i++) {
            tmp *= 10;
        }
        return tmp;
    }

    private static void outputNumArr(int[] arr) {
        int i = 0;
        while (i < arr.length && arr[i] == 0) {
            i++;
        }
        if (i == arr.length) // all elements are zero
            System.out.print(0);
        else {
            while (i < arr.length) {
                System.out.print(arr[i]);
                i++;
            }
        }
        System.out.println();
    }

    private static void increment(int[] arr) {
        int carry = 0, tmp, i = arr.length - 1;
        do {
            tmp = arr[i];
            if (i == arr.length - 1) {
                arr[i] = (tmp + 1) % 10;
                carry = (tmp + 1) / 10;
            } else {
                arr[i] = (tmp + carry) % 10;
                carry = (tmp + carry) / 10;
            }
            i--;
        } while (i >= 0 && carry != 0);
    }

    public static void testOutPutOneToN() {
        outputOneToN(6);
    }

    /**
     * @param arr
     * @param m
     * @param n
     */
    public static void printRotaryMatrix(int[][] arr, int m, int n) {
        int i, j, k, count = 0;
        for (i = 0; i * 2 < m; i++) {
            for (j = i; j < n - i && count < m * n; j++) { // 若最后一次输出为这句，会有问题，待解决
                System.out.printf("%6d", arr[i][j]);
                count++;
            }
            System.out.println();

            for (k = i + 1; k < m - i && count < m * n; k++) {
                System.out.printf("%6d", arr[k][n - i - 1]);
                count++;
            }
            System.out.println();

            for (j = n - i - 2; j > i - 1 && count < m * n; j--) {
                System.out.printf("%6d", arr[m - i - 1][j]);
                count++;
            }
            System.out.println();

            for (k = m - i - 2; k > i && count < m * n; k--) {
                System.out.printf("%6d", arr[k][i]);
                count++;
            }
            System.out.println();
        }
    }

    public static int[][] generateRandomized2DArray(int m, int n, int MAX_NUM) {
        int[][] arr = new int[m][n];
        Random rand = new Random(MAX_NUM);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = rand.nextInt(MAX_NUM);
            }
        }
        return arr;
    }

    private static void print2DArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.printf("%6d", arr[i][j]);
            }
            System.out.println();
        }
    }

    private static void swap(int[][] arr, int i1, int j1, int i2, int j2) {
        int tmp = arr[i1][j1];
        arr[i1][j1] = arr[i2][j2];
        arr[i2][j2] = tmp;
    }

    public static void testPrintRotaryMatrix() {
        int m = 6, n = 3, maxNum = 100;
        int[][] arr = generateRandomized2DArray(m, n, maxNum);
        print2DArray(arr);
        System.out.println("--------------------------------------------");
        printRotaryMatrix(arr, m, n);
    }

    /**
     * calculate a is divided by b
     * and print the quotient and remain
     * without using multiply or divide operator
     *
     * @param a
     * @param b
     */
    public static void divide(int a, int b) {
        int quotient = 0, remain;
        if (b == 0) {
            System.out.printf("divide number cannot be 0!\n");
        } else if (a == 0) {
            System.out.printf("quotient: %d remain: %d\n", 0, 0);

        } else if (a * b < 0) {
            remain = a;
            while (Math.abs(remain) >= Math.abs(b)) {
                remain += b;
                quotient--;
            }
            System.out.printf("quotient: %d remain: %d\n", quotient, remain);
        } else {
            a = Math.abs(a);
            b = Math.abs(b);
            remain = a;
            while (remain >= b) {
                remain -= b;
                quotient++;
            }
            System.out.printf("quotient: %d remain: %d\n", quotient, remain);
        }

    }

//    public static boolean isSequence(char[] arr) {
//
//    }

    /**
     * you have an array called A[N], each element is known.
     * now assign array B[N], which satisfies B[i] = A[1]*A[2]*...*A[i-1]*A[i+1]*...*A[N].
     * demands: do not use divide, time complexity limitation O(n), and space complexity limitation O(1)
     *
     * @param arr
     * @return
     */
    public static int[] multiplyAssign(int[] arr) {
        int[] B = new int[arr.length];
        int left = 1, right = 1;
        for (int i = 0; i < arr.length; i++) {
            B[i] = 1;
        }

        for (int i = 0; i < arr.length; i++) {
            B[i] *= left;
            B[arr.length - i - 1] *= right;
            left *= arr[i];
            right *= arr[arr.length - i - 1];
        }
        return B;
    }

    public static void testMultiply() {
        int[] A = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] B = multiplyAssign(A);
        Sort.printArray(B);
    }

    /**
     * Rotate a matrix by 90 deg, clockwise.
     * Time complexity O(n^2), Space Complexity O(1).
     *
     * @param matrix
     */
    public static void rotateMatrix(int[][] matrix) {
        int m, n, i, k;
        int[] tmp;
        if (matrix == null) {
            return;
        } else {
            m = matrix.length;
            n = matrix[0].length;
            if (m != n) {
                return;
            } else {
                tmp = new int[n];
                for (k = 0; k * 2 < n; k++) {
                    for (i = k; i < n - k - 1; i++) {
//                        tmp[i - k] = matrix[k][i];
//                        matrix[k][i] = matrix[n - 1 - i][k];
//                        matrix[n - 1 - i][k] = matrix[n - 1 - k][n - 1 - i];
//                        matrix[n - 1 - k][n - 1 - i] = matrix[n - 1 - i][n - 1 - k];
//                        matrix[n - 1 - i][n - 1 - k] = tmp[i - k];
                        swap(matrix, k, i, i, n - 1 - k);
                        swap(matrix, k, i, n - 1 - i, k);
                        swap(matrix, n - 1 - i, k, n - 1 - k, n - 1 - i);
                    }
                }
            }
        }
    }

    public static void testRotateMatrix() {
        int n = 6;
        int[][] matrix = new int[n][n];
        Random rand = new Random(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = i * n + j;//rand.nextInt(100);
            }
        }
        print2DArray(matrix);
        rotateMatrix(matrix);
        System.out.println("-------------------------------------");
        print2DArray(matrix);
    }
}
