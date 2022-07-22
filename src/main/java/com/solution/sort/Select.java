package com.solution.sort;

/**
 * Created by dell on 2017/4/24.
 */
public class Select {
    public static int randomizedSelect(int[] arr, int p, int r, int i) {
        int k, q;
        if (p == r)
            return p;
        q = Sort.randomizedPartition(arr, p, r);
        k = q - p + 1;

        if (k > i) {
            return randomizedSelect(arr, p, q - 1, i);
        } else if (k < i) {
            return randomizedSelect(arr, q + 1, r, i - k);
        } else {
            return q;
        }
    }

    public static void testPartitionSelect() {
        int[] arr = Sort.generateRandomArray(30, 500);
        int kth = 6;
        int index = randomizedSelect(arr, 0, arr.length - 1, kth);
        System.out.println("Array:");
        Sort.printArray(arr);
        Sort.quickSort(arr, 0, arr.length - 1);
        Sort.printArray(arr);
        System.out.printf("The %dth least index is %d, the value is %d\n", kth, index, arr[index]);
    }

    public static void insertionSort(int[] arr, int p, int r) {
        int tmp, i, j;
        if (p < r && r < arr.length) {
            for (i = p + 1; i <= r; i++) {
                tmp = arr[i];
                j = i - 1;
                while (j >= p && arr[j] > tmp) {
                    arr[j + 1] = arr[j];
                    j--;
                }
                arr[j + 1] = tmp;
            }
        }
    }

    public static int findMedian(int[] arr, int p, int r) {
        // There is 5 elements in every group.
        final int NUM_PER_GROUP = 5;
        // Then the group num is n.
        int groupNum = arr.length / NUM_PER_GROUP;
        int remain = arr.length % NUM_PER_GROUP;
        return 0;
    }

    public static int selectPartition(int[] arr, int p, int r, int x) {
        Sort.swap(arr, r, x);
        return Sort.partition(arr, p, r);
    }

    /**
     * return the index of the kth least element in an array arr
     * worst time complexity is O(n)
     *
     * @param arr
     * @param kth
     * @return the index of the kth least element in an array arr
     */
    public static int select5(int[] arr, int p, int r, int kth) {
        int index = 0;
        final int THERMAL = 75;
        /**
         * There is 5 elements in every group.
         */
        final int NUM_PER_GROUP = 5;
        int groupNum = arr.length / NUM_PER_GROUP;
        int remain = arr.length % NUM_PER_GROUP;

        if (r - p < THERMAL) {
            insertionSort(arr, p, r);
            return p - kth + 1;
        }

        for (int i = 0; i < (r - p - NUM_PER_GROUP + 1) / NUM_PER_GROUP; i++) {
            insertionSort(arr, p + i * NUM_PER_GROUP, p + i * NUM_PER_GROUP + NUM_PER_GROUP - 1);
            Sort.swap(arr, p + i * NUM_PER_GROUP + (NUM_PER_GROUP) / 2, p + i);
        }

        index = select5(arr, p, p + (r - p - NUM_PER_GROUP + 1) / NUM_PER_GROUP, (r - p - NUM_PER_GROUP + 1) / 10);

        int i = selectPartition(arr, p, r, index);
        int j = i - p + 1;
        if (kth <= j) {
            return select5(arr, p, i, kth);
        } else {
            return select5(arr, i + 1, r, kth - j);
        }

    }
}
