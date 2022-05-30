package archive.karat;

public class Majiang {

  public class CompleteHand {


    //valid hand contains exactly one pair and the rest are triples find if hand is valid hand
    //"11122" => True

    // Count each digit, ignore triplets by count %= 3, and track number of remaining pairs.
    public boolean completeHand(String hand) {
      if (hand == null || hand.length() % 3 != 2) {
        return false;
      }

      int pairs = 0;

      int count = 0; // count of current repeated number
      char prev = hand.charAt(0);
      for (int i = 0; i <= hand.length(); i++) {
        if (i == hand.length() || hand.charAt(i) != prev) { // reaching end or encountering a different character
          count %= 3;
          if (count == 1) {
            return false;
          } else if (count == 2) {
            pairs++;
          }

          count = 1;
          if (i < hand.length()) {
            prev = hand.charAt(i);
          }
        } else {
          count++;
        }
      }
      return pairs == 1;
    }

    // matrix of numbers, given index i, j, find the number of neighbors with the same value as matrix[i][j]

    // DFS, time O(N^2), space O(1)
    public int numSameNeighbors(int[][] matrix, int i, int j) {
      if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        return 0;
      }
      return dfs(matrix, i, j, matrix[i][j]);
    }

    private final int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // recursively find number of cells equal to target, and market visited cell with -1 to avoid re-visiting it.
    private int dfs(int[][] matrix, int i, int j, int target) {
      int m = matrix.length;
      int n = matrix[0].length;
      if (i < 0 || i >= m || j < 0 || j >= n || matrix[i][j] == -1) {
        return 0;
      }
      int count = matrix[i][j] == target ? 1 : 0;
      matrix[i][j] = -1; // if cannot mutate matrix, use a visited[][] matrix instead

      for (int[] dir: dirs) {
        count += dfs(matrix, i + dir[0], j + dir[1], target);
      }
      return count;
    }

    // Part 3: same as 1. but we can have 3 consecutives
    // "56788822" => True
    // "67888" => True
    // "678888" => False

    // Backtracking DFS
    // First try to take a pair, then try taking all 3 consecutives, and finally check if remaining are all triplets.
    // Complexity:
    // Suppose hand length is 3N + 2
    // Time: O(9 * 7^N) = O(7^N)
    public boolean completeHandAdvanced(String hand) {
      if (hand.length() % 3 != 2) {
        return false;
      }

      int[] count = new int[10];
      for (char c: hand.toCharArray()) {
        count[c - '0']++;
      }

      for (int i = 1; i < 10; i++) {
        if (count[i] >= 2) {
          count[i] -= 2; // take a pair
          if (allTripleOrConsecutive3(count)) {
            return true;
          }
          count[i] += 2; // backtrack
        }
      }
      return false;
    }

    private boolean allTripleOrConsecutive3(int[] count) {
      // try taking all consecutive 3s
      for (int i = 1; i <= 7; i++) {
        if (count[i] > 0 && count[i + 1] > 0 && count[i + 2] > 0) {
          count[i]--;
          count[i + 1]--;
          count[i + 2]--;

          if (allTripleOrConsecutive3(count)) {
            return true;
          }

          // backtrack
          count[i]++;
          count[i + 1]++;
          count[i + 2]++;
        }
      }
      // check if only triples remaining
      for (int num: count) {
        if (num % 3 != 0) {
          return false;
        }
      }
      return true;
    }

  }
}
