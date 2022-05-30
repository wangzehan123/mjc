package archive.bolt.tag;

import java.util.ArrayList;
import java.util.Arrays;

public class MergeInterval_WordSearch {

  public int[][] merge(int[][] intervals) {
    Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
    ArrayList<int[]> ans  =  new ArrayList<>();

    int start = intervals[0][0];
    int end = intervals[0][1];

    int i = 1;
    while (i < intervals.length) {
      int s = intervals[i][0];
      int e = intervals[i][1];

      if (s <= end) {
        end = Math.max(end, e);
      }else {
        ans.add(new int[]{start, end});
        start = s;
        end = e;
      }
      i++;
    }

    ans.add(new int[] {start, end});
    return ans.toArray(new int[ans.size()][]);
  }

  public boolean exist(char[][] board, String word) {
    // write your code here
    if (board == null || board.length == 0 || board[0].length == 0) {
      return false;
    }

    int m = board.length, n = board[0].length;
    boolean[][] visited = new boolean[m][n];

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        visited[i][j] = true; // avoid to visit first point every time
        if (dfs(board, 0, word, i, j, visited)) {
          return true;
        }
        visited[i][j] = false;
      }
    }

    return false;
  }

  int[] dirX = {1, 0, 0, -1};
  int[] dirY = {0, -1, 1, 0};
  private boolean dfs(char[][] board, int index, String word, int x, int y, boolean[][] visited) {
    char ch = board[x][y];
    if (ch != word.charAt(index)) {
      return false;
    }
    if (index == word.length() - 1) {
      return true;
    }

    for (int i = 0; i < 4; i++) {
      int adjx = x + dirX[i];
      int adjy = y + dirY[i];

      if (!isBounded(adjx, adjy, board) || visited[adjx][adjy]) {
        continue;
      }

      visited[adjx][adjy] = true;
      if (dfs(board, index + 1, word, adjx, adjy, visited)) {
        return true;
      }
      visited[adjx][adjy] = false;
    }

    return false;
  }

  private boolean isBounded(int x, int y, char[][] board) {
    int m = board.length, n = board[0].length;
    return x >= 0 && x < m && y >=0 && y < n;
  }

}
