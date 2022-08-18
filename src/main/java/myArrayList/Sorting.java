package myArrayList;

import java.util.Arrays;
import java.util.Random;

public class Sorting {
    public static void main(String[] args) {

        double iteration = 5;
        int sizeArray = 10_000;
        long timeSortBubble = 0;
        long timeSortSelection = 0;
        long timeSortInsertion = 0;
        long timeQuickSort = 0;
        long timeMergeSort = 0;

        for (int i = 0; i < iteration; i++) {
            int[] testArray = generateArray(sizeArray);
            int[] arrayForSortBubble = Arrays.copyOf(testArray, testArray.length);
            int[] arrayForSortSelection = Arrays.copyOf(testArray, testArray.length);
            int[] arrayForSortInsertion = Arrays.copyOf(testArray, testArray.length);
            int[] arrayQuickSort = Arrays.copyOf(testArray, testArray.length);
            int[] arrayMergeSort = Arrays.copyOf(testArray, testArray.length);

            long startForSortBubble = System.currentTimeMillis();
            sortBubble(arrayForSortBubble);
            timeSortBubble += System.currentTimeMillis() - startForSortBubble;

            long startForSortSelection = System.currentTimeMillis();
            sortSelection(arrayForSortSelection);
            timeSortSelection += System.currentTimeMillis() - startForSortSelection;

            long startForSortInsertion = System.currentTimeMillis();
            sortInsertion(arrayForSortInsertion);
            timeSortInsertion += System.currentTimeMillis() - startForSortInsertion;

            long startForQuickSort = System.currentTimeMillis();
            quickSort(arrayQuickSort, 1, sizeArray - 1);
            timeQuickSort += System.currentTimeMillis() - startForQuickSort;

            long startForMergeSort = System.currentTimeMillis();
            mergeSort(arrayMergeSort);
            timeMergeSort += System.currentTimeMillis() - startForMergeSort;
        }
        System.out.println("averageValuetimeSortBubble = " + (timeSortBubble / iteration));
        System.out.println("averageValuetimeSortSelection = " + (timeSortSelection / iteration));
        System.out.println("averageValuetimeSortInsertion = " + (timeSortInsertion / iteration));
        System.out.println("averageValuetimeQuickSort = " + (timeQuickSort / iteration));
        System.out.println("averageValuetimeMergeSort = " + (timeMergeSort / iteration));
    }

    private static final Random RANDOM = new Random();

    private static int[] generateArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = RANDOM.nextInt(50000);
        }
        return array;
    }

    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    private static void sortBubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    private static void sortSelection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    private static void sortInsertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    public static void quickSort(int[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(int[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                swapElements(arr, i, j);
            }
        }
        swapElements(arr, i + 1, end);
        return i + 1;
    }

    public static void mergeSort(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        int mid = arr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];
        for (int i = 0; i < left.length; i++) {
            left[i] = arr[i];
        }
        for (int i = 0; i < right.length; i++) {
            right[i] = arr[mid + i];
        }
        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }

    public static void merge(int[] arr, int[] left, int[] right) {

        int mainP = 0;
        int leftP = 0;
        int rightP = 0;
        while (leftP < left.length && rightP < right.length) {
            if (left[leftP] <= right[rightP]) {
                arr[mainP++] = left[leftP++];
            } else {
                arr[mainP++] = right[rightP++];
            }
        }
        while (leftP < left.length) {
            arr[mainP++] = left[leftP++];
        }
        while (rightP < right.length) {
            arr[mainP++] = right[rightP++];
        }
    }
}
