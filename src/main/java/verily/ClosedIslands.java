package verily;

//First, we need to remove all land connected to the edges using flood fill.
//
//Then, we can count and flood-fill the remaining islands.

public class ClosedIslands {

  class Solution {
    int[] directions = new int[] {0, 1, 0, -1, 0};

    public int closedIsland(int[][] g) {
      for (int i = 0; i < g.length; i++) {
        for (int j = 0; j < g[i].length; j++) {
          if (i == 0 || j == 0 || i == g.length - 1 || j == g[i].length - 1) {
            fill(g, i, j);
          }
        }
      }
      int res = 0;
      for (int i = 0; i < g.length; ++i) {
        for (int j = 0; j < g[i].length; ++j) {
          if (g[i][j] == 0) {
            res++;
            fill(g, i, j);
          }
        }
      }

      return res;
    }

    private void fill(int[][] g, int x, int y) {
      if (x < 0 || y < 0 || x >= g.length || y >= g[0].length || g[x][y] == 1)
        return;
      g[x][y] = 1;
      for (int i = 0; i < directions.length - 1; i++) {
        fill(g, x + directions[i], y + directions[i + 1]);
      }
    }
  }
}
