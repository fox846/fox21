import java.util.Scanner;
public class q5Knapsack {
    public static int knapsack(int[] weights, int[] values, int capacity, int n) {
        // DP table to store the maximum value for each subproblem
        int[][] dp = new int[n + 1][capacity + 1]; //1 for 0 in start

        // Build the table dp[][] in a bottom-up manner
        for (int i = 0; i <= n; i++) { //row
            for (int j = 0; j <= capacity; j++) { //column
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;  // Base case: no items or capacity 0
                } else if (weights[i - 1] <= j) {
                    // If current item's weight is less than or equal to the capacity
                    dp[i][j] = Math.max(dp[i - 1][j] ,values[i - 1] + dp[i - 1][j - weights[i - 1]]);
                } else {
                    // If current item's weight is greater than the remaining capacity
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // Display selected items
        System.out.print("Items included in the knapsack: ");
        int j = capacity;
        for (int i = n; i > 0 && j > 0; i--) {
            if (dp[i][j] != dp[i - 1][j]) {
                System.out.print(i + " ");  // Item index (1-based)
                j -= weights[i - 1];       // Reduce the remaining capacity
            }
        }
        System.out.println();

        return dp[n][capacity];  // Maximum value that can be obtained
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();

        System.out.print("Enter the capacity of the knapsack: ");
        int capacity = scanner.nextInt();

        // Arrays to store weights and values of items
        int[] weights = new int[n];
        int[] values = new int[n];

        // Input weights and values of items
        System.out.println("Enter the weights and values of items:");
        for (int i = 0; i < n; i++) {
            System.out.print("Item : " + (i + 1) + "  weight : ");
            weights[i] = scanner.nextInt();
            System.out.print("Item : " + (i + 1) + "  value : ");
            values[i] = scanner.nextInt();
        }
        // Call knapsack function and display the result
        int maxValue = knapsack(weights, values, capacity, n);
        System.out.println("Maximum value that can be obtained: " + maxValue);

        scanner.close();
    }
}

