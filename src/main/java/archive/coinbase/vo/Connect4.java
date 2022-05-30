package archive.coinbase.vo;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Connect4 {
  public static void main(String[] args) {
    Connect4 connect4 = new Connect4(6, 7);
    Random random = new Random(5);
    boolean flag = false;
    while (!flag) {
      Scanner in = new Scanner(System.in);
      System.out.print("choose a column: ");
      flag = connect4.move(in.nextInt());
    }
//    while (!connect4.move(random.nextInt(7))) {
//    }

    System.out.println("finally!");
  }

  private final char[][] board;
  private char player;
  private final int[] depth;

  public Connect4(int row, int col) {

    board = new char[row][col];
    player = 'x';
    depth = new int[col];
    for (int i = 0; i < col; i++) {
      depth[i] = row - 1;
    }
  }

  public boolean move(int col) {
    if (col > board[0].length) {
      throw new IllegalArgumentException("column out of bounds");
    }
    if (depth[col] < 0) {
      boolean isFull = true;
      for (int i = 0; i < col; i++) {
        if (depth[i] >= 0) {
          isFull = false;
        }
      }
      if (isFull) {
        throw new IllegalArgumentException("The board is full");
      }
      throw new IllegalArgumentException("This column is full");
    }


    if (player == 'x') {
      player = 'o';
    } else {
      player = 'x';
    }
    int currentRow = depth[col];
    depth[col]--;
    board[currentRow][col] = player;
    System.out.printf("add %s to row %d col %d %n", player, currentRow, col);
    for (char[] b : board) {
      System.out.println(Arrays.toString(b));
    }
    System.out.println(isWinner(currentRow, col));
    return isWinner(currentRow, col);
  }


  private boolean isWinner(int row, int col) {

    String target = String.format("%s%s%s%s", player, player, player, player);

    if (checkRow(row, target)) {
      return true;
    }

    if (checkCol(col, target)) {
      return true;
    }
    if (checkDiag(row, col, target)) {
      return true;
    }

    return false;
  }

  private boolean isInArea(int row, int col) {
    if (row >= 0 && col >= 0 && row < board.length && col < board[0].length) {
      return true;
    }
    return false;
  }

  private boolean checkRow(int row, String target) {
    // check row
    char[] rowArr = board[row];
    String rowString = String.valueOf(rowArr);
    if (rowString.contains(target)) {
      System.out.println("winner is " + player + " with horizontal");
      return true;
    }
    return false;
  }

  private boolean checkCol(int col, String target) {
    //check column
    StringBuilder colString = new StringBuilder();
    for (int i = 0; i < board.length; i++) {
      colString.append(board[i][col]);
    }
    if (colString.toString().contains(target)) {
      System.out.println("winner is " + player + " with vertical");
      return true;
    }
    return false;
  }

  private boolean checkDiag(int row, int col, String target) {
    // check diagonal
    StringBuilder diag1 = new StringBuilder();
    StringBuilder diag2 = new StringBuilder();

    int tmpRow1 = row, tmpCol1 = col;
    int tmpRow2 = row, tmpCol2 = col;

    for (int i = -3; i < 4; i++) {
      // top left to bottom right
      tmpRow1 += i;
      tmpCol1 += i;
      if (isInArea(tmpRow1, tmpCol1)) {
        diag1.append(board[tmpRow1][tmpCol1]);
      }
      tmpRow1 -= i;
      tmpCol1 += i;

      // bottom left to top right
      tmpRow2 -= i;
      tmpCol2 += i;
      if (isInArea(tmpRow2, tmpCol2)) {
        diag2.append(board[tmpRow2][tmpCol2]);
      }
      tmpRow2 += i;
      tmpCol2 -= i;
    }

    if (diag1.toString().contains(target)) {
      System.out.println("winner is " + player + " with diag1");
      return true;
    }
    if (diag2.toString().contains(target)) {
      System.out.println("winner is " + player + " with diag2");
      return true;
    }
    return false;
  }
}
