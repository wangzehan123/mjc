package archive.doordash;

/*
* 对于这道题目来说，我们要明确几点
目标(base case) ：填满每个格子。
选择列表(choice list)：每个空的格子可以填入1-9这九个数字。
约束条件(constrain)：1-9在每一行、每一列和每个3x3的大格中都只能出现一次。
选择路径(path)：由于我们是inplace操作，board就存储了已经做过的选择。
我们在这里设置backtrack函数的返回类型为bool，这样一来，当程序找到可行解后就能终止递归。
* 从最左上角的方格开始，即i = 0, j = 0，开始一行一行填充。
corner case：
如果j = 9，说明越界，从下一行的第0列开始填。
如果i = 9，说明找到可行解，返回True。
如果`board != 0，这个格子不需要我们填，于是去填下一个格子。
从1到9迭代循环数组，尝试放置数字val进入(i, j)的格子。
如果符合约束条件：
将val放置在(i, j)的格子中。
进行dfs，对后面的格子进行相同的操作。
判断是否能找出数独的解。如果可以，返回True。
进行回溯，即将(i, j)恢复为0。
如果遍历完1-9都没有找到数独的解，返回False。
* 时间复杂度：时间复杂度是常数。由于数独的大小是固定的，因此没有 N 变量来衡量。上限是
。对第一行来说，第一个格子不会超过9种情况，第二个格子不会超过8种...所以第一行不会超过
个情况。那么所有行不会超过 Max = (9!)^9
空间复杂度：数独大小固定，空间用来存储数独，行，列和子方块的结构，每个有 81 个元素。
* */
public class SudokuSolver {
  /**
   * @param board: the sudoku puzzle
   * @return: nothing
   */
  public void solveSudoku(int[][] board) {
    backtrack(board, 0, 0);
  }
  boolean backtrack(int[][] board, int i, int j) {
    int m = 9, n = 9;
    // 到达第n列，越界，换到下一行第0列重新开始
    if (j == n) {
      return backtrack(board, i + 1, 0);
    }
    // 到达第m行，说明找到可行解，触发 base case
    if (i == m) {
      return true;
    }

    // 如果有预设数字，不用我们穷举
    if (board[i][j] != 0) {
      return backtrack(board, i, j + 1);
    }
    for (int num = 1; num <= 9; num++) {
      // 如果遇到不合法的数字，就跳过
      if (!isValid(board, i, j, num))
        continue;

      // 添加选择
      board[i][j] = num;
      // 如果找到一个可行解，立即结束
      if (backtrack(board, i, j + 1)) {
        return true;
      }
      // 撤回选择
      board[i][j] = 0;
    }
    // 穷举完1~9，依然没有找到可行解，此路不通
    return false;
  }
  boolean isValid(int[][] board, int row, int col, int val) {
    for (int i = 0; i < 9; i++) {
      // 判断行是否存在重复
      if (board[row][i] == val) {
        return false;
      }
      // 判断列是否存在重复
      if (board[i][col] == val) {
        return false;
      }
      // 判断 3 x 3 方框是否存在重复
      if (board[(row / 3) * 3 + i / 3][(col / 3) * 3 + i % 3] == val)
        return false;
    }
    return true;
  }
}
