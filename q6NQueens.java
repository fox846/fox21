import java.util.Arrays;

public class q6NQueens {
    // Check if it's safe to place a queen
    private static boolean safe(int[][] board, int r, int c, int n) {
        // Check the current row on the left
        for (int i = 0; i < c; i++) if (board[r][i] == 1) return false;

        // Check upper-left diagonal
        for (int i = r, j = c; i >= 0 && j >= 0; i--, j--) 
        if (board[i][j] == 1) 
        return false;

        // Check lower-left diagonal
        for (int i = r, j = c; i < n && j >= 0; i++, j--) 
        if (board[i][j] == 1)
         return false;

        return true; // Safe to place the queen
    }
    // Solve the N-Queens problem using backtracking
    private static boolean solve(int[][] board, int col, int n) {
        if (col >= n) 
        return true; // All queens are placed successfully

        for (int row = 0; row < n; row++) {
            if (safe(board, row, col, n)) { // Check if it's safe to place a queen
                board[row][col] = 1; // Place the queen
                if (solve(board, col + 1, n)) 
                return true; // Recurse for the next column
                board[row][col] = 0; // Backtrack and remove the queen
            }
        }
        return false; // No valid placement
    }
    // Initialize board, measure time, and solve
    public static void run(int n) {
        int[][] board = new int[n][n]; // Create an NxN board
        long start = System.nanoTime(); // Start time
        boolean found = solve(board, 0, n); // Start solving from column 0
        long end = System.nanoTime(); // End time

        if (found) {
            System.out.println("Solution for " + n + "-Queens:");
            for (int[] row : board) System.out.println(Arrays.toString(row)); // Print solution
        } else {
            System.out.println("No solution exists for " + n + "-Queens.");
        }
        System.out.printf("Time for %d-Queens: %.2f ms\n", n, (end - start) / 1_000_000.0);
    }
    public static void main(String[] args) {
        int[] sizes = {4, 5, 6, 7, 8}; // List of board sizes
        for (int n : sizes) run(n); // Run for each size
    }
}