package linkedin.math;

public class BulbSwitcher {


  /**
   * @param n: a Integer
   * @return: how many bulbs are on after n rounds
   */

  /*
  * A lightbulb stays on if we flip it even times. At i th rounds, we flip a lightbulk that contains factor i , so now the question becomes how to determine the total number of number <= n that
  * contains even number of factors excluding 1, or in other words, odd number of factors including 1.
  * Any positive whole number can be expressed as num = a^x * b^y * ... where a, b, ... are primes, and its number of factors can be determined as K = (x + 1) * (y + 1) *...,
  * we want K to be an odd number, so it means x, y, ..., they all have to be even. In other words, num has to be a perfect square.
  * */

  public int bulbSwitch(int n) {
    // Write your code here
    return (int) Math.sqrt(n);
  }
}
