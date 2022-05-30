package archive.opendoor;

public class GameOfLife {

  /**
   * @param board: the given board
   * @return: nothing
   */
  public void gameOfLife(int[][] board) {
    // Write your code here

    char w = 'a';

    if (board == null || board.length == 0) {
      return;
    }
    int m = board.length, n = board[0].length;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        // 计算与board[i][j]相邻的活细胞数量
        int lives = liveNeighbors(board, m, n, i, j);

        // 如果当前位置为活细胞，且相邻活细胞数量为2个或者3个，则下一状态仍为活细胞
        if (board[i][j] == 1 && lives >= 2 && lives <= 3) {
          board[i][j] = 3;
        }

        // 如果当前位置为死细胞，且相邻活细胞数量为3个，则下一状态为活细胞
        if (board[i][j] == 0 && lives == 3) {
          board[i][j] = 2;
        }
      }
    }

    // 更新细胞状态
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        board[i][j] >>= 1;
      }
    }
  }

  public int liveNeighbors(int[][] board, int m, int n, int i, int j) {
    int lives = 0;
    for (int x = Math.max(i - 1, 0); x <= Math.min(i + 1, m - 1); x++) {
      for (int y = Math.max(j - 1, 0); y <= Math.min(j + 1, n - 1); y++) {
        lives += board[x][y] & 1;
      }
    }
    lives -= board[i][j] & 1;
    return lives;
  }
}
