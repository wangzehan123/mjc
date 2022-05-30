package archive.karat;

import java.util.ArrayList;
import java.util.List;

public class Rectangles {

  public static void main(String[] args) {
    int[][] board = {{1,1,1,1,1,1}, {0,0,1,0,1,1}, {0,0,1,0,1,0}, {1,1,1,0,1,0}, {1,0,0,1,1,1}};
    List<Integer> res = findRectangle(board);
    List<List<Integer>> rectangles = findRectangles(board);
    List<List<Integer>> shapes = findShapes(board);
    System.out.println(rectangles);
    System.out.println(shapes);
    }

  public static List<Integer> findRectangle(int[][] board) {
    if (board == null || board.length == 0 || board[0].length == 0) {
      return new ArrayList<>();
    }

    List<Integer> res = new ArrayList<>();
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        if (board[i][j] == 0) {
          res.add(i);
          res.add(j);
          int height = 1;
          int width = 1;
          while (i + height < board.length && board[i + height][j] == 0) {
            height++;
          }
          while (j + width < board[0].length && board[i][j + width] == 0) {
            width++;
          }
          res.add(i + height - 1);
          res.add(j + width - 1);
          break;
        }
        if (res.size() != 0) {
          break;
        }
      }
    }
    return res;
  }

  public static List<List<Integer>> findRectangles(int[][] board) {
    if (board == null || board.length == 0 || board[0].length == 0) {
      return new ArrayList<>();
    }
    List<List<Integer>> res = new ArrayList<>();
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        if (board[i][j] == 0) {
          List<Integer> rectangle = new ArrayList<>();
          rectangle.add(i);
          rectangle.add(j);
          board[i][j] = 1;
          int height = 1, width = 1;
          while (i + height < board.length && board[i + height][j] == 0) {
            height++;
          }
          while (j + width < board[0].length && board[i][j + width] == 0) {
            width++;
          }
          for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
              board[i + h][j + w] = 1;
            }
          }
          rectangle.add(i + height - 1);
          rectangle.add(j + width - 1);
          res.add(rectangle);
        }
      }
    }
    return res;
  }

  public static List<List<Integer>> findShapes(int[][] board) {
    if (board == null || board.length == 0 || board[0].length == 0) {
      return new ArrayList<>();
    }
    List<List<Integer>> res = new ArrayList<>();
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        List<Integer> shape = new ArrayList<>();
        dfs(i, j , shape, board);
        res.add(shape);
      }
    }
    return res;
  }

  public static void dfs (int x, int y, List<Integer> shape, int[][] board) {
    if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] == 1) {
      return;
    }
    board[x][y] = 1;
    shape.add(x);
    shape.add(y);
    dfs(x + 1, y, shape, board);
    dfs(x - 1, y, shape, board);
    dfs(x, y - 1, shape, board);
    dfs(x, y + 1, shape, board);
  }
}
