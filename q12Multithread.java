public class q12Multithread {
  private static final int MATRIX_SIZE = 3; // 3x3 matrix
  private static final int NUM_THREADS = 2; // Number of threads for parallel computation
  public static void main(String[] args) {
      int[][] matrix1 = {
          {1, 2, 3},
          {4, 5, 6},
          {7, 8, 9}
      };

      int[][] matrix2 = {
          {9, 8, 7},
          {6, 5, 4},
          {3, 2, 1}  
      };

      int[][] resultParallel = new int[MATRIX_SIZE][MATRIX_SIZE];
      int[][] resultSequential = new int[MATRIX_SIZE][MATRIX_SIZE];

      System.out.println("Matrix 1:");
      printMatrix(matrix1);

      System.out.println("\nMatrix 2:");
      printMatrix(matrix2);

      // Time multithreaded matrix multiplication
      long startParallel = System.nanoTime();
      parallelMultiply(matrix1, matrix2, resultParallel);
      long endParallel = System.nanoTime();

      // Time sequential matrix multiplication
      long startSequential = System.nanoTime();
      sequentialMultiply(matrix1, matrix2, resultSequential);
      long endSequential = System.nanoTime();

      // Print results
      System.out.println("\nResult (Parallel):");
      printMatrix(resultParallel);
      System.out.println("Time taken (Parallel): " + (endParallel - startParallel) + " ns");

      System.out.println("\nResult (Sequential):");
      printMatrix(resultSequential);
      System.out.println("Time taken (Sequential): " + (endSequential - startSequential) + " ns");
  }

  public static void parallelMultiply(int[][] matrix1, int[][] matrix2, int[][] result) {
      Thread[] threads = new Thread[NUM_THREADS];
      int segmentSize = MATRIX_SIZE / NUM_THREADS;

      for (int i = 0; i < NUM_THREADS; i++) {
          int startIndex = i * segmentSize;
          int endIndex = (i == NUM_THREADS - 1) ? MATRIX_SIZE - 1 : (startIndex + segmentSize - 1);
          //create and start
          threads[i] = new Thread(new MultiplicationTask(matrix1, matrix2, result, startIndex, endIndex));
          threads[i].start();
      }
      //complete
      for (Thread thread : threads) {
          try {
              thread.join();
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
  }

  public static void sequentialMultiply(int[][] matrix1, int[][] matrix2, int[][] result) {
      int cols = matrix2[0].length;
      for (int i = 0; i < MATRIX_SIZE; i++) {
          for (int j = 0; j < cols; j++) {
              for (int k = 0; k < MATRIX_SIZE; k++) {
                  result[i][j] += matrix1[i][k] * matrix2[k][j];
              }
          }
      }
  }

  static void printMatrix(int[][] matrix) {
      for (int[] row : matrix) {
          for (int element : row) {
              System.out.print(element + " ");
          }
          System.out.println();
      }
  }

  static class MultiplicationTask implements Runnable {
      private int[][] matrix1;
      private int[][] matrix2;
      private int[][] result;
      private int startIndex;
      private int endIndex;

      public MultiplicationTask(int[][] matrix1, int[][] matrix2, int[][] result, int startIndex, int endIndex) {
          this.matrix1 = matrix1;
          this.matrix2 = matrix2;
          this.result = result;
          this.startIndex = startIndex;
          this.endIndex = endIndex;
      }

      @Override
      public void run() {
          int cols = matrix2[0].length;
          for (int i = startIndex; i <= endIndex; i++) {
              for (int j = 0; j < cols; j++) {
                  for (int k = 0; k < MATRIX_SIZE; k++) {
                      result[i][j] += matrix1[i][k] * matrix2[k][j];
                  }
              }
          }
      }
  }
}
