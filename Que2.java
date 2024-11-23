import java.util.*;
/* 
PART A: Implement QuickSort using divide and conquer strategy.
PART B: Compare its time required wrt Merge sort OR Randomized Quick Sort
*/
// import java.util.*;
public class Que2 {
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int p = partition(arr, low, high);
            quickSort(arr, low, p - 1); 
            quickSort(arr, p + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) { //j=low pasun start hotay 
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void randomizedQuickSort(int[] arr, int low, int high) {
        if (low < high) {
            int p = randomizedPartition(arr, low, high);
            randomizedQuickSort(arr, low, p - 1);
            randomizedQuickSort(arr, p + 1, high);
        }
    }

    public static int randomizedPartition(int[] arr, int low, int high) {
        Random rand = new Random();
        int randomIndex = low + rand.nextInt(high - low + 1); // Generate random pivot index
        swap(arr, randomIndex, high); // Swap pivot with the last element
        return partition(arr, low, high);
    }

    public static void main(String[] args) {
        int n = 500;
        int[] sample = new int[n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            sample[i] = random.nextInt(1000);
        }

        int[] mergeSample = sample.clone(); //create copy of sample array 
        System.out.println("------------ Using Quick sort --------------");
        for (int i : sample) { //print initial array
            System.out.print(i + " ");
        }

        long startTime = System.nanoTime(); //records start time before calling quicksort
        quickSort(sample, 0, n - 1); //call quicksort to sort sample array
        long endTime = System.nanoTime(); //record end time and calculates the elapsed time for Quick Sort in nanoseconds.
        long timeElapsed = endTime - startTime;
        System.out.println();
        System.out.println("Sorting the array using Quick sort");
        for (int i : sample) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("Time taken for Quick sort: " + timeElapsed + " ns");


        System.out.println("------------ Using randomized quick sort --------------");
        for (int i : mergeSample) { //print initial unsorted array
            System.out.print(i + " ");
        }

        startTime = System.nanoTime();
        randomizedQuickSort(mergeSample, 0, n - 1);
        endTime = System.nanoTime();
        timeElapsed = endTime - startTime;
        System.out.println();
        System.out.println("Sorting the array using random quick sort");
        for (int i : mergeSample) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("Time taken for random quick sort sort: " + timeElapsed + " ns");

    }
}
