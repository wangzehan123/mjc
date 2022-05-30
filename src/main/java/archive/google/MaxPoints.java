package archive.google;

public class MaxPoints {

  public long maxPoints(int[][] points) {
    // idea - each row we need to pick one
    // 3 options we have for the next row, same col or +1 or -1 col
    // as target for max, we just use DP/Memory to save the max value for each cell in row from 3 cases
    // then just continue it till last row -- then filter the global row out of it
    int m = points.length, n = points[0].length;
    long[] memo = new long[n];

    for (int i = 0; i < m; i++) {
      // base case - just add the one on the same col
      for (int j = 0; j < n; j++) {
        memo[j] += points[i][j];
      }
      // 2 cases with -1 penalty
      for (int j = 1; j < n; j++) {
        memo[j] = Math.max(memo[j], memo[j - 1] - 1);
      }
      for (int j = n - 2; j >= 0; j--) {
        memo[j] = Math.max(memo[j], memo[j + 1] - 1);
      }
    }

    // filter out max (don't forget long!)
    long max = memo[0];
    for (int j = 1; j < n; j++) {
      max = Math.max(max, memo[j]);
    }

    return max;
  }
}
