package com.solution;

public class Temp {

    public static void main(String[] args) {
        int[] a = {4, 3, 2, 1};
        qsort(a, 0, a.length - 1);
        for (int i : a) {
            System.out.print(i + ' ');
        }
    }

    public static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void qsort(int[] a, int start, int end) {
        if (start >= end) {
            return;
        }

        int temp = a[start];
        int i = start + 1;
        int j = end;

        while (i < j) {
            while (i < j && a[i] < temp) {
                i++;
            }

            while (i < j && a[j] > temp) {
                j--;
            }

        }

    }
}
