package sort;

import java.util.Arrays;
import java.util.Random;
import java.lang.Integer;

/**
 * Created by dell on 2017/4/20.
 */
public class Sort {
    public static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            System.out.printf("%6d", arr[i]);
        System.out.println();
    }

    /**
     * sort #1
     *
     * @param arr
     */
    public static void insertionSort(int[] arr) {
        int i, j;
        int tmp;

        for (i = 1; i < arr.length; i++) {
            tmp = arr[i];
            for (j = i - 1; j >= 0 && tmp < arr[j]; j--) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = tmp;
        }
    }

    /**
     * sort #2
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        boolean moved = true;
        for (int i = arr.length; i > 0 && moved; i--) {
            moved = false;
            for (int j = 1; j < i; j++) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                    moved = true;
                }
            }
        }
    }

    /**
     * sort #3
     *
     * @param arr
     * @param maxNum
     */
    public static void countSort(int[] arr, int maxNum) {
        int[] c = new int[maxNum];
        int[] b = Arrays.copyOf(arr, arr.length);

        for (int i = 0; i < b.length; i++) {
            c[b[i]]++;
        }

        for (int i = 1; i < c.length; i++) {
            c[i] += c[i - 1];
        }

        for (int i = arr.length - 1; i > -1; i--) {
            arr[c[b[i]] - 1] = b[i];
            c[b[i]]--;
        }
    }

    /**
     * sort #4
     *
     * @param arr
     * @param p
     * @param q
     * @param r
     */
    private static void merge(int[] arr, int p, int q, int r) {
        int[] b = new int[q - p + 1];
        int[] c = new int[r - q];
        int i, j, k;
        for (i = p; i <= q; i++) {
            b[i - p] = arr[i];
        }
        for (i = q + 1; i <= r; i++) {
            c[i - q- 1] = arr[i];
        }
//Arrays.sort(arr);
        i = 0;
        j = 0;
        k = p;
        while (i < b.length && j < c.length && k <= r) {
            if (b[i] < c[j])
                arr[k++] = b[i++];
            else
                arr[k++] = c[j++];
        }

        while (i < b.length && k <= r)
            arr[k++] = b[i++];
        while (j < c.length && k <= r)
            arr[k++] = c[j++];

    }

    public static void mergeSort(int[] arr, int p, int r) {
        int q;
        if (p < r) {
            q = (p + r) / 2;
            mergeSort(arr, p, q);
            mergeSort(arr, q + 1, r);
            merge(arr, p, q, r);
        }
    }

    /**
     * sort #5
     *
     * @param arr
     */
    public static void selectionSort(int[] arr) {
        int tmp;
        for (int i = 0; i < arr.length; i++) {
            tmp = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[tmp])
                    tmp = j;
            }
            swap(arr, i, tmp);
        }
    }

    /**
     * sort #6
     *
     * @param arr 待排序的数组
     * @param p   起始下标
     * @param r   终止下标
     * @return 分界点
     */
    public static int partition(int[] arr, int p, int r) {
        int i = p - 1, j;
        int key = arr[r];

        for (j = p; j < r; j++) {
            if (arr[j] < key)
                swap(arr, ++i, j);
        }
        swap(arr, i + 1, r);
        return i + 1;
    }

    /**
     * partition随机版
     *
     * @param arr
     * @param p
     * @param r
     * @return
     */
    public static int randomizedPartition(int[] arr, int p, int r) {
        Random rand = new Random();
        int i = rand.nextInt(r - p) + p; // PS: 要生成介于p和r之间的随机数
        swap(arr, i, r);
        return partition(arr, p, r);
    }

    public static void quickSort(int[] arr, int p, int r) {
        int q;
        if (p < r) {
            q = partition(arr, p, r);
            quickSort(arr, p, q - 1);
            quickSort(arr, q + 1, r);
        }
    }

    public static void dualPivotQuickSort(int[] a, int left, int right, boolean leftmost) {
        int length = right - left + 1;
        if (length < 47) {
            insertionSort(a);
        } else {
            // Inexpensive approximation of length / 7
            int seventh = (length >> 3) + (length >> 6) + 1;

            /*
             * Sort five evenly spaced elements around (and including) the
             * center element in the range. These elements will be used for
             * pivot selection as described below. The choice for spacing
             * these elements was empirically determined to work well on
             * a wide variety of inputs.
             */
            int e3 = (left + right) >>> 1; // The midpoint
            int e2 = e3 - seventh;
            int e1 = e2 - seventh;
            int e4 = e3 + seventh;
            int e5 = e4 + seventh;

            // Sort these elements using insertion sort
            if (a[e2] < a[e1]) {
                int t = a[e2];
                a[e2] = a[e1];
                a[e1] = t;
            }

            if (a[e3] < a[e2]) {
                int t = a[e3];
                a[e3] = a[e2];
                a[e2] = t;
                if (t < a[e1]) {
                    a[e2] = a[e1];
                    a[e1] = t;
                }
            }
            if (a[e4] < a[e3]) {
                int t = a[e4];
                a[e4] = a[e3];
                a[e3] = t;
                if (t < a[e2]) {
                    a[e3] = a[e2];
                    a[e2] = t;
                    if (t < a[e1]) {
                        a[e2] = a[e1];
                        a[e1] = t;
                    }
                }
            }
            if (a[e5] < a[e4]) {
                int t = a[e5];
                a[e5] = a[e4];
                a[e4] = t;
                if (t < a[e3]) {
                    a[e4] = a[e3];
                    a[e3] = t;
                    if (t < a[e2]) {
                        a[e3] = a[e2];
                        a[e2] = t;
                        if (t < a[e1]) {
                            a[e2] = a[e1];
                            a[e1] = t;
                        }
                    }
                }
            }

            // Pointers
            int less = left;  // The index of the first element of center part
            int great = right; // The index before the first element of right part

            if (a[e1] != a[e2] && a[e2] != a[e3] && a[e3] != a[e4] && a[e4] != a[e5]) {
                /*
                 * Use the second and fourth of the five sorted elements as pivots.
                 * These values are inexpensive approximations of the first and
                 * second terciles of the array. Note that pivot1 <= pivot2.
                 */
                int pivot1 = a[e2];
                int pivot2 = a[e4];

                /*
                 * The first and the last elements to be sorted are moved to the
                 * locations formerly occupied by the pivots. When partitioning
                 * is complete, the pivots are swapped back into their final
                 * positions, and excluded from subsequent sorting.
                 */
                a[e2] = a[left];
                a[e4] = a[right];

                /*
                 * Skip elements, which are less or greater than pivot values.
                 */
                while (a[++less] < pivot1) ;
                while (a[--great] > pivot2) ;

                /*
                 * Partitioning:
                 *
                 *   left part           center part                   right part
                 * +--------------------------------------------------------------+
                 * |  < pivot1  |  pivot1 <= && <= pivot2  |    ?    |  > pivot2  |
                 * +--------------------------------------------------------------+
                 *               ^                          ^       ^
                 *               |                          |       |
                 *              less                        k     great
                 *
                 * Invariants:
                 *
                 *              all in (left, less)   < pivot1
                 *    pivot1 <= all in [less, k)     <= pivot2
                 *              all in (great, right) > pivot2
                 *
                 * Pointer k is the first index of ?-part.
                 */
                outer:
                for (int k = less - 1; ++k <= great; ) {
                    int ak = a[k];
                    if (ak < pivot1) { // Move a[k] to left part
                        a[k] = a[less];
                        /*
                         * Here and below we use "a[i] = b; i++;" instead
                         * of "a[i++] = b;" due to performance issue.
                         */
                        a[less] = ak;
                        ++less;
                    } else if (ak > pivot2) { // Move a[k] to right part
                        while (a[great] > pivot2) {
                            if (great-- == k) {
                                break outer;
                            }
                        }
                        if (a[great] < pivot1) { // a[great] <= pivot2
                            a[k] = a[less];
                            a[less] = a[great];
                            ++less;
                        } else { // pivot1 <= a[great] <= pivot2
                            a[k] = a[great];
                        }
                        /*
                         * Here and below we use "a[i] = b; i--;" instead
                         * of "a[i--] = b;" due to performance issue.
                         */
                        a[great] = ak;
                        --great;
                    }
                }

                // Swap pivots into their final positions
                a[left] = a[less - 1];
                a[less - 1] = pivot1;
                a[right] = a[great + 1];
                a[great + 1] = pivot2;

                // Sort left and right parts recursively, excluding known pivots
                dualPivotQuickSort(a, left, less - 2, leftmost);
                dualPivotQuickSort(a, great + 2, right, false);

                /*
                 * If center part is too large (comprises > 4/7 of the array),
                 * swap internal pivot values to ends.
                 */
                if (less < e1 && e5 < great) {
                    /*
                    * Skip elements, which are equal to pivot values.
                    */
                    while (a[less] == pivot1) {
                        ++less;
                    }

                    while (a[great] == pivot2) {
                        --great;
                    }

                    /*
                     * Partitioning:
                     *
                     *   left part         center part                  right part
                     * +----------------------------------------------------------+
                     * | == pivot1 |  pivot1 < && < pivot2  |    ?    | == pivot2 |
                     * +----------------------------------------------------------+
                     *              ^                        ^       ^
                     *              |                        |       |
                     *             less                      k     great
                     *
                     * Invariants:
                     *
                     *              all in (*,  less) == pivot1
                     *     pivot1 < all in [less,  k)  < pivot2
                     *              all in (great, *) == pivot2
                     *
                     * Pointer k is the first index of ?-part.
                     */
                    outer:
                    for (int k = less - 1; ++k <= great; ) {
                        int ak = a[k];
                        if (ak == pivot1) { // Move a[k] to left part
                            a[k] = a[less];
                            a[less] = ak;
                            ++less;
                        } else if (ak == pivot2) { // Move a[k] to right part
                            while (a[great] == pivot2) {
                                if (great-- == k) {
                                    break outer;
                                }
                            }
                            if (a[great] == pivot1) { // a[great] < pivot2
                                a[k] = a[less];
                                /*
                                 * Even though a[great] equals to pivot1, the
                                 * assignment a[less] = pivot1 may be incorrect,
                                 * if a[great] and pivot1 are floating-point zeros
                                 * of different signs. Therefore in float and
                                 * double sorting methods we have to use more
                                 * accurate assignment a[less] = a[great].
                                 */
                                a[less] = pivot1;
                                ++less;
                            } else { // pivot1 < a[great] < pivot2
                                a[k] = a[great];
                            }
                            a[great] = ak;
                            --great;
                        }
                    }
                }

                // Sort center part recursively
                dualPivotQuickSort(a, less, great, false);

            } else { // Partitioning with one pivot
                /*
                 * Use the third of the five sorted elements as pivot.
                 * This value is inexpensive approximation of the median.
                 */
                int pivot = a[e3];

                /*
                 * Partitioning degenerates to the traditional 3-way
                 * (or "Dutch National Flag") schema:
                 *
                 *   left part    center part              right part
                 * +-------------------------------------------------+
                 * |  < pivot  |   == pivot   |     ?    |  > pivot  |
                 * +-------------------------------------------------+
                 *              ^              ^        ^
                 *              |              |        |
                 *             less            k      great
                 *
                 * Invariants:
                 *
                 *   all in (left, less)   < pivot
                 *   all in [less, k)     == pivot
                 *   all in (great, right) > pivot
                 *
                 * Pointer k is the first index of ?-part.
                 */
                for (int k = less; k <= great; ++k) {
                    if (a[k] == pivot) {
                        continue;
                    }
                    int ak = a[k];
                    if (ak < pivot) { // Move a[k] to left part
                        a[k] = a[less];
                        a[less] = ak;
                        ++less;
                    } else { // a[k] > pivot - Move a[k] to right part
                        while (a[great] > pivot) {
                            --great;
                        }
                        if (a[great] < pivot) { // a[great] <= pivot
                            a[k] = a[less];
                            a[less] = a[great];
                            ++less;
                        } else { // a[great] == pivot
                            /*
                             * Even though a[great] equals to pivot, the
                             * assignment a[k] = pivot may be incorrect,
                             * if a[great] and pivot are floating-point
                             * zeros of different signs. Therefore in float
                             * and double sorting methods we have to use
                             * more accurate assignment a[k] = a[great].
                             */
                            a[k] = pivot;
                        }
                        a[great] = ak;
                        --great;
                    }
                }
                /*
                 * Sort left and right parts recursively.
                 * All elements from center part are equal
                 * and, therefore, already sorted.
                 */
                dualPivotQuickSort(a, left, less - 1, leftmost);
                dualPivotQuickSort(a, great + 1, right, false);
            }
        }
    }

    /**
     * sort #7
     *
     * @param arr
     */
    public static void shellSort(int[] arr) {
        int gap = arr.length;
        boolean flag;
        while (gap > 0) {
            gap /= 2;
            do {
                flag = false;
                for (int i = 0; i < arr.length - gap; i++) {
                    if (arr[i] > arr[i + gap]) {
                        swap(arr, i, i + gap);
                        flag = true;
                    }
                }
            } while (flag);
        }
    }

    /**
     * sort #8
     *
     * @param arr
     * @param p
     * @param r
     */
    private static void adjust(int[] arr, int p, int r) {
        int j, tmp;

        tmp = arr[p];
        j = 2 * p + 1;
        while (j <= r) {
            if (j < r && arr[j] < arr[j + 1])
                j++;
            if (tmp >= arr[j])
                break;
            arr[(j - 1) / 2] = arr[j];
            j = 2 * j + 1;
        }
        arr[(j - 1) / 2] = tmp;
    }

    public static void heapSort(int[] arr) {
        for (int i = arr.length / 2; i >= 0; i--)
            adjust(arr, i, arr.length - 1);

        for (int i = arr.length - 2; i >= 0; i--) {
            swap(arr, 0, i + 1);
            adjust(arr, 0, i);
            printArray(arr);
        }
    }

    public static int[] generateRandomArray() {
        final int ARRAY_NUM = 16;
        final int MAX_NUM = 100;
        Random rand = new Random(ARRAY_NUM);
        int[] arr = new int[ARRAY_NUM];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(MAX_NUM);
        }
        return arr;
    }

    public static int[] generateRandomArray(int arrayNum, int maxNum) {
        Random rand = new Random(arrayNum);
        int[] arr = new int[arrayNum];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(maxNum);
        }
        return arr;
    }

    public static void testInsertionSort() {
        int[] arr = generateRandomArray();
        printArray(arr);
        insertionSort(arr);
        printArray(arr);
    }

    public static void testBubbleSort() {
        int[] arr = generateRandomArray();
        printArray(arr);
        bubbleSort(arr);
        printArray(arr);
    }

    public static void testCountSort() {
        Random rand = new Random();
        final int ARRAY_NUM = 20;
        final int MAX_NUM = 15;
        int[] arr = new int[ARRAY_NUM];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(MAX_NUM);
        }
        printArray(arr);
        countSort(arr, MAX_NUM);
        printArray(arr);
    }

    public static void testSelectionSort() {
        int[] arr = generateRandomArray();
        printArray(arr);
        selectionSort(arr);
        printArray(arr);
    }

    public static void testMergeSort() {
        int[] arr = generateRandomArray();
        printArray(arr);
        mergeSort(arr, 0, arr.length - 1);
        printArray(arr);
    }

    public static void testQuickSort() {
        int[] arr = generateRandomArray();
        printArray(arr);
        quickSort(arr, 0, arr.length - 1);
        printArray(arr);
    }

    public static void testShellSort() {
        int[] arr = generateRandomArray();
        printArray(arr);
        shellSort(arr);
        printArray(arr);
    }

    public static void testHeapSort() {
        int[] arr = generateRandomArray();
        printArray(arr);
        heapSort(arr);
//        printArray(arr);
    }

    public static void testArraySort() {
        int[] arr = generateRandomArray(50, 500);
        printArray(arr);
        dualPivotQuickSort(arr, 0, arr.length - 1, true);
        printArray(arr);
    }
}
