package archive.karat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PrimitiveIterator;
import java.util.Set;

public class Suduko {

  public class ValidMatrix {

    //Part 1: 给一个N*N的矩阵，判定是否是有效的矩阵。有效矩阵的定义是每一行或者每一列的数字都必须正好是1到N的数。输出一个bool。

    // Check each row and column, it's valid iff:
    // * each element is within [1, n]
    // * no duplicates
    public boolean isValidMatrix(int[][] matrix) {
      if (matrix == null || matrix.length == 0 || matrix[0].length == 0 || matrix.length != matrix[0].length) {
        return false;
      }
      int n = matrix.length;

      for (int i = 0; i < n; i++) {
        Set<Integer> rowSet = new HashSet<>();
        Set<Integer> colSet = new HashSet<>();
        for (int j = 0; j < n; j++) {
          // check row
          int x = matrix[i][j];
          if (!rowSet.add(x) || x  < 1 || x > n) {
            return false;
          }
          // check column
          int y = matrix[j][i];
          if (!colSet.add(y) || y < 1 || y > n) {
            return false;
          }
        }
      }
      return true;
    }

    /**
     * Part 2
     * A nonogram is a logic puzzle, similar to a crossword, in which the player is given a blank grid and has to
     * color it according to some instructions. Specifically, each cell can be either black or white, which we will
     * represent as 0 for black and 1 for white.
     *
     * +------------+
     * | 1  1  1  1 |
     * | 0  1  1  1 |
     * | 0  1  0  0 |
     * | 1  1  0  1 |
     * | 0  0  1  1 |
     * +------------+
     *
     * For each row and column, the instructions give the lengths of contiguous runs of black (0) cells.
     * For example, the instructions for one row of [ 2, 1 ] indicate that there must be a run of two black cells,
     * followed later by another run of one black cell, and the rest of the row filled with white cells.
     *
     * These are valid solutions: [ 1, 0, 0, 1, 0 ] and [ 0, 0, 1, 1, 0 ] and also [ 0, 0, 1, 0, 1 ]
     * This is not valid: [ 1, 0, 1, 0, 0 ] since the runs are not in the correct order.
     * This is not valid: [ 1, 0, 0, 0, 1 ] since the two runs of 0s are not separated by 1s.
     *
     * Your job is to write a function to validate a possible solution against a set of instructions.
     *
     * Given a 2D matrix representing a player's solution; and instructions for each row along with additional
     * instructions for each column; return True or False according to whether both sets of instructions match.
     *
     * Example instructions #1
     *
     * matrix1 = [[1,1,1,1],
     *            [0,1,1,1],
     *            [0,1,0,0],
     *            [1,1,0,1],
     *            [0,0,1,1]]
     * rows1_1    =  [], [1], [1,2], [1], [2]
     * columns1_1 =  [2,1], [1], [2], [1]
     * validateNonogram(matrix1, rows1_1, columns1_1) => True
     *
     * Example solution matrix:
     * matrix1 ->
     *                                    row
     *                 +------------+     instructions
     *                 | 1  1  1  1 | <-- []
     *                 | 0  1  1  1 | <-- [1]
     *                 | 0  1  0  0 | <-- [1,2]
     *                 | 1  1  0  1 | <-- [1]
     *                 | 0  0  1  1 | <-- [2]
     *                 +------------+
     *                   ^  ^  ^  ^
     *                   |  |  |  |
     *   column       [2,1] | [2] |
     *   instructions      [1]   [1]
     *
     *
     * Example instructions #2
     *
     * (same matrix as above)
     * rows1_2    =  [], [], [1], [1], [1,1]
     * columns1_2 =  [2], [1], [2], [1]
     * validateNonogram(matrix1, rows1_2, columns1_2) => False
     *
     * The second and third rows and the first column do not match their respective instructions.
     *
     * Example instructions #3
     *
     * matrix2 = [
     * [ 1, 1 ],
     * [ 0, 0 ],
     * [ 0, 0 ],
     * [ 1, 0 ]
     * ]
     * rows2_1    = [], [2], [2], [1]
     * columns2_1 = [1, 1], [3]
     * validateNonogram(matrix2, rows2_1, columns2_1) => False
     *
     * The black cells in the first column are not separated by white cells.
     *
     * n: number of rows in the matrix
     * m: number of columns in the matrix
     *
     * 作者：关辰晓
     * 链接：https://www.jianshu.com/p/fdbcba5fe5bc
     * 来源：简书
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     */
    public boolean validateNonogram(int[][] matrix, int[][] rows, int[][] cols) {
      if (matrix == null || rows == null || cols == null || matrix.length == 0 || matrix[0].length == 0
          || rows.length != matrix.length || cols.length != matrix[0].length) {
        return false;
      }
      int m = matrix.length;
      int n = matrix[0].length;
      // check rows
      for (int i = 0; i < m; i++) {
        if (!isValid(Arrays.stream(matrix[i]).iterator(), rows[i])) {
          return false;
        }
      }
      // check columns
      for (int j = 0; j < n; j++) {
        if (!isValid(new ColumnIterator(matrix, j), cols[j])) {
          return false;
        }
      }
      return true;
    }

    private boolean isValid(PrimitiveIterator.OfInt nums, int[] runs) {
      int curZeros = 0; // count of zeros in current run
      int runIdx = 0; // index of run in instruction array runs

      while (nums.hasNext()) {
        int num = nums.next();
        if (num == 1) {
          if (curZeros > 0) { // ending a run
            if (runIdx >= runs.length || runs[runIdx] != curZeros) {
              return false;
            }
            runIdx++;
            curZeros = 0;
          }
        } else { // starting or extending a run
          curZeros++;
        }
      }
      // finishing last run
      if (curZeros > 0) {
        if (runIdx >= runs.length || runs[runIdx] != curZeros) {
          return false;
        }
        runIdx++;
      }

      return runIdx == runs.length; // check if all runs finished
    }

    class ColumnIterator implements PrimitiveIterator.OfInt {
      private final int[][] matrix;
      private final int col;
      private int row;

      public ColumnIterator(int[][] matrix, int col) {
        this.matrix = matrix;
        this.col = col;
        this.row = 0;
      }

      @Override
      public int nextInt() {
        return matrix[row++][col];
      }

      @Override
      public boolean hasNext() {
        return row < matrix.length;
      }
    }
  }

}
