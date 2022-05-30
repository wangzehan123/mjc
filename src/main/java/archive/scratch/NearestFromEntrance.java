package archive.scratch;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class NearestFromEntrance {

  public int nearestExit(char[][] maze, int[] entrance) {
    int[] direX = {1, -1, 0, 0};
    int[] direY = {0, 0, 1, -1};
    int result = 0;
    int m = maze.length;
    int n = maze[0].length;
    Queue<Cell> queue = new LinkedList<>();
    boolean[][] visited = new boolean[m][n];
    Cell root = new Cell(entrance[0],entrance[1]);
    queue.offer(root);
    visited[entrance[0]][entrance[1]] = true;
    while (!queue.isEmpty()) {
      int size = queue.size();
      result++;

      for (int index = 0; index < size; index++) {
        Cell curr = queue.poll();
        for (int i = 0; i < 4; i++) {
          int x = direX[i] + curr.x;
          int y = direY[i] + curr.y;
          if (x < 0 || x >= m || y < 0 || y >= n) {
            continue;
          }
          if (maze[x][y] == '+' || visited[x][y]) {
            continue;
          }
          if (x == 0 || x == m - 1 || y == 0 || y == n - 1) {
            return result;
          }
          queue.offer(new Cell(x,y));
          visited[x][y] = true;
        }
      }
    }
    return -1;
  }

  class Cell {
    Character symbol;
    int x;
    int y;

    public Cell(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

}
