import java.util.*;

public class q4merge_crossover {

    // Merge Sort function
    public static void mergeSort(int[] arr, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);
            merge(arr, low, mid, high);
        }
    }

    // Merge function to combine two sorted halves
    public static void merge(int[] arr, int low, int mid, int high) {
        int n1 = mid - low + 1;
        int n2 = high - mid;

        int[] arr1 = new int[n1];
        int[] arr2 = new int[n2];

        int arrInd = low;
        for (int i = 0; i < n1; i++) {
            arr1[i] = arr[arrInd++];
        }

        arrInd = mid + 1;
        for (int i = 0; i < n2; i++) {
            arr2[i] = arr[arrInd++];
        }

        int ind1 = 0, ind2 = 0;
        arrInd = low;
        while (ind1 < n1 && ind2 < n2) {
            if (arr1[ind1] <= arr2[ind2]) {
                arr[arrInd++] = arr1[ind1++];
            } else {
                arr[arrInd++] = arr2[ind2++];
            }
        }

        while (ind1 < n1) {
            arr[arrInd++] = arr1[ind1++];
        }

        while (ind2 < n2) {
            arr[arrInd++] = arr2[ind2++];
        }
    }

    // Wrapper function for mergeSort
    public static void performMergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Taking input for array size
        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();
        scanner.close();

        // Best case: Already sorted array
        int[] bestCaseArray = new int[size];
        for (int i = 0; i < size; i++) {
            bestCaseArray[i] = i;
        }

        long startTime = System.nanoTime();
        performMergeSort(bestCaseArray);
        long endTime = System.nanoTime();
        System.out.println("Merge Sort Best Case Time: " + (endTime - startTime) + " nanoseconds");

        // Worst case: Reverse sorted array
        int[] worstCaseArray = new int[size];
        for (int i = 0; i < size; i++) {
            worstCaseArray[i] = size - i - 1;
        }

        startTime = System.nanoTime();
        performMergeSort(worstCaseArray);
        endTime = System.nanoTime();
        System.out.println("Merge Sort Worst Case Time: " + (endTime - startTime) + " nanoseconds");
    }
}


//tsp crossover

import java.util.*;

public class MS_crossover {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of cities: ");
        int n = sc.nextInt();

        int[] parent1 = new int[n];
        int[] parent2 = new int[n];

        System.out.println("Enter the Parent 1 Chromosome (tour): ");
        for (int i = 0; i < n; i++) {
            parent1[i] = sc.nextInt();
        }

        System.out.println("Enter the Parent 2 Chromosome (tour): ");
        for (int i = 0; i < n; i++) {
            parent2[i] = sc.nextInt();
        }

        int[] child = crossover(parent1, parent2);

        System.out.println("Parent 1 Chromosome: ");
        System.out.println(Arrays.toString(parent1));

        System.out.println("Parent 2 Chromosome: ");
        System.out.println(Arrays.toString(parent2));

        System.out.println("Child Chromosome after Crossover: ");
        System.out.println(Arrays.toString(child));

        sc.close();
    }

    public static int[] crossover(int[] parent1, int[] parent2) {
        int n = parent1.length;
        int[] child = new int[n];
        Arrays.fill(child, -1); // Initialize child array with -1

        Random rand = new Random();
        int start = rand.nextInt(n);  // Random start point
        int end = rand.nextInt(n);    // Random end point

        // Ensure start < end
        if (start > end) {
            int temp = start;
            start = end;
            end = temp;
        }

        // Copy segment from Parent 1 to Child
        for (int i = start; i <= end; i++) {
            child[i] = parent1[i];
        }

        // Fill remaining positions from Parent 2, maintaining order
        HashSet<Integer> set = new HashSet<>();
        for (int i = start; i <= end; i++) {
            set.add(child[i]);
        }

        int childIndex = 0;
        for (int i = 0; i < n; i++) {
            if (child[i] == -1) {
                // Fill from Parent 2
                while (set.contains(parent2[childIndex])) {
                    childIndex++;
                }
                child[i] = parent2[childIndex++];
            }
        }

        return child;
    }
}
