package com.solution.sort;

public class Perm {

    protected static void log(String format, Object... args) {
        System.out.printf(format, args);
    }

    protected static int[] make(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        return arr;
    }

    protected static void visit(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            visit(arr[i]);
        }
        System.out.println();
    }

    protected static void visit(int i) {
        System.out.printf("%d ", i);
    }

    public static void perm(int n) {
        int[] arr = make(n);
        perm(arr, 0, arr.length);
    }

    protected static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void perm(int[] arr, int i, int n) {
        if (i >= n) {
            return;
        } else if (i == n - 1) {
            visit(arr);
        }

        for (int k = i; k < n; k++) {
            swap(arr, i, k);
            perm(arr, i + 1, n);
            swap(arr, i, k);
        }
    }


    public static boolean canMove(int[] arr, boolean[] left, int i) {
        if (i == 0) {
            return !left[i] && arr[i] > arr[i + 1];
        }

        if (i == arr.length - 1) {
            return left[i] && arr[i] > arr[i - 1];
        }

        return left[i] ? arr[i] > arr[i - 1] : arr[i] > arr[i + 1];
    }

    public static int findMovableIndex(int[] arr, boolean[] left) {
        int val = -1;
        int movableIndex = -1;
        for (int i = 0; i < arr.length; i++) {
            if (canMove(arr, left, i)) {
                if (val < arr[i]) {
                    val = arr[i];
                    movableIndex = i;
                }
            }
        }

        return movableIndex;
    }

    /**
     * Johnson Trotter 算法
     */
    public static void johnsonTrotter(int n) {
        int[] arr = make(n);
        boolean[] left = new boolean[n];
        for (int i = 0; i < n; i++) {
            left[i] = true;
        }

        visit(arr);
        int movableIndex = findMovableIndex(arr, left);
        if (movableIndex == -1) {
            return;
        }

        if (left[movableIndex]) {
            swap(arr, movableIndex, movableIndex - 1);
        } else {
            swap(arr, movableIndex, movableIndex + 1);
        }
    }

    public static void main(String[] args) {
        johnsonTrotter(4);
    }
}
