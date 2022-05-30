package archive.bolt.tag;

import java.util.ArrayList;
import java.util.List;

public class FindAllPathSum {

  static List<List<Integer>> arr = new ArrayList<>();
  static List<List<Integer>> result = new ArrayList<>();

  public int minPathSum(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (i == 0 && j != 0) {
          grid[i][j] += grid[i][j - 1];
        }
        if (i != 0 && j == 0) {
          grid[i][j] += grid[i - 1][j];
        }
        if (i != 0 && j != 0) {
          grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
        }
      }
    }
    return grid[m - 1][n - 1];
  }

  public static void findPaths(int[][] mat, List<Integer> list, int i, int j) {
    // base case
    if (mat == null || mat.length == 0) {
      return;
    }
    int M = mat.length;
    int N = mat[0].length;
    // if the last cell is reached, print the route
    if (i == M - 1 && j == N - 1) {
      list.add(mat[i][j]);
      result.add(new ArrayList<>(list));
      list.remove(list.size() - 1);
      return;
    }
    // include the current cell in the path
    list.add(mat[i][j]);

    // move right
    if ((i >= 0 && i < M && j + 1 >= 0 && j + 1 < N)) {
      findPaths(mat, list, i, j + 1);
    }
    // move down
    if ((i + 1 >= 0 && i + 1 < M && j >= 0 && j < N)) {
      findPaths(mat, list, i + 1, j);
    }
    // backtrack: remove the current cell from the path
    list.remove(list.size() - 1);
  }
  public static void main(String[] args) {
    int[][] mat =
        {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
    // start from `(0, 0)` cell
    int x = 0, y = 0;
    findPaths(mat, new ArrayList<>(), x, y);
    System.out.println(result);
  }
}
