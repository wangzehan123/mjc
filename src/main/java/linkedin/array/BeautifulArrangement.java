package linkedin.array;

public class BeautifulArrangement {

  int count = 0;
  public int countArrangement(int N) {
    int[] res = new int[N];
    helper(res, N);
    return count;
  }

  public void helper(int[] res, int N) {
    if (N == 0) {
      count++;
      return;
    }
    for (int i = 0; i < res.length; i++) {
      if (res[i] == 0 || (N % (i + 1) == 0 || (i + 1) % N == 0)) {
        res[i] = N;
        helper(res, N - 1);
        res[i] = 0;
      }
    }
  }
}
