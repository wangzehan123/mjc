package archive.doordash;

import java.util.HashSet;
import java.util.Set;
/*
* 这题考查的是二维数组的遍历顺序。
判断每一行是否合法，外层循环枚举行，内层循环枚举列。
判断每一列是否合法，外层循环枚举列，内层循环枚举行。
判断每一块是否合法，每一块左上角的坐标都是(0, 0), (3, 0), (6, 0), (3, 0), (3, 3) ...，可以发现都是3的倍数，可以总结出规律，
* 先枚举i = [0, 1, 2]，再枚举j = [0, 1, 2]，左上角坐标就是(i**3, j****3)。
* */
public class Sudoku {

    public boolean isValidSudoku(char[][] board) {
      Set<Character> used = new HashSet();
      // 先枚举行，检查每行是否合法
      for (int row = 0; row < 9; row++) {
        used.clear();
        for (int col = 0; col < 9; col++) {
          if (! checkValid(board[row][col], used)) {
            return false;
          }
        }
      }
      // 先枚举列，检查每列是否合法
      for (int col = 0; col < 9; col++) {
        used.clear();
        for (int row = 0; row < 9; row++) {
          if (! checkValid(board[row][col], used)) {
            return false;
          }
        }
      }
      // 每个分块的左上角的坐标为(i * 3, j * 3)
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          used.clear();
          for (int row = i * 3; row < i * 3 + 3; row++) {
            for (int col = j * 3; col < j * 3 + 3; col++) {
              if (! checkValid(board[row][col], used)) {
                return false;
              }
            }
          }
        }
      }
      return true;
    }
    // 检查字符是否有冲突
    boolean checkValid(char c, Set<Character> used) {
      if (c == '.') {
        return true;
      }
      if (used.contains(c)) {
        return false;
      }
      used.add(c);
      return true;
    }
}
