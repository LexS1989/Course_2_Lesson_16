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

        for (int i = 0; i < iteration; i++) {
            int[] testArray = generateArray(sizeArray);
            int[] arrayForSortBubble = Arrays.copyOf(testArray, testArray.length);
            int[] arrayForSortSelection = Arrays.copyOf(testArray, testArray.length);
            int[] arrayForSortInsertion = Arrays.copyOf(testArray, testArray.length);

            long startForSortBubble = System.currentTimeMillis();
            sortBubble(arrayForSortBubble);
            timeSortBubble += System.currentTimeMillis() - startForSortBubble;

            long startForSortSelection = System.currentTimeMillis();
            sortSelection(arrayForSortSelection);
            timeSortSelection += System.currentTimeMillis() - startForSortSelection;

            long startForSortInsertion = System.currentTimeMillis();
            sortInsertion(arrayForSortInsertion);
            timeSortInsertion += System.currentTimeMillis() - startForSortInsertion;
        }
        System.out.println("averageValuetimeSortBubble = " + (timeSortBubble / iteration));
        System.out.println("averageValuetimeSortSelection = " + (timeSortSelection / iteration));
        System.out.println("averageValuetimeSortInsertion = " + (timeSortInsertion / iteration));
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
}
