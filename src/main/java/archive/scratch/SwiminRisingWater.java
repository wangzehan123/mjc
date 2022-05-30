package archive.scratch;

import java.util.Comparator;
import java.util.PriorityQueue;

public class SwiminRisingWater {

  class Cell {
    int x;
    int y;
    int t;

    public Cell(int x, int y, int t) {
      this.x = x;
      this.y = y;
      this.t = t;
    }
  }
  public int swimInWater(int[][] grid) {
    int[] direX = {1, -1, 0, 0};
    int[] direY = {0, 0, 1, -1};
    PriorityQueue<Cell> pq = new PriorityQueue<>(new Comparator<Cell>() {
      @Override
      public int compare(Cell o1, Cell o2) {
        return o1.t - o2.t;
      }
    });

    int m = grid.length;
    int n = grid[0].length;
    int res = 0;
    boolean[][] visited = new boolean[m][n];
    visited[0][0] = true;
    pq.add(new Cell(0,0,grid[0][0]));
    while (!pq.isEmpty()) {
      Cell current = pq.poll();
      res = Math.max(res, current.t);
      if (current.x == m - 1 && current.y == n - 1) {
        return res;
      }
      for (int i = 0; i < 4; i++) {
        int x = current.x + direX[i];
        int y = current.y + direY[i];
        if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]) {
          visited[x][y] = true;
          pq.add(new Cell(x, y, grid[x][y]));
        }
      }
    }
    return -1;
  }

}
