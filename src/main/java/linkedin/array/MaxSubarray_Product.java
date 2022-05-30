package linkedin.array;

public class MaxSubarray_Product {

  public int maxSubArray(int[] A) {
    if (A == null || A.length == 0){
      return 0;
    }
    //max记录全局最大值，sum记录区间和，如果当前sum>0，那么可以继续和后面的数求和，否则就从0开始
    int max = Integer.MIN_VALUE, sum = 0;
    for (int i = 0; i < A.length; i++) {
      sum += A[i];
      max = Math.max(max, sum);
      sum = Math.max(sum, 0);
    }

    return max;
  }

  /*
  * 比较容易想出来的线性dp。由于数据中有正有负，所以我们利用两个dp数组来完成。用fi来保存计算到第i个时的最大值，用gi来保持计算到第i个时的最小值。
  * 在得出了第i-1的dp值之后，即包含i-1的可能值区间为[gi-1,fi-1]（左闭右闭区间）。我们考虑第i个数的情况。
  * 若numsi为正数，可能值区间为[gi-1×numsi,fi-1×numsi]，和numsi。
  * 若numsi为负数，可能值区间为[fi-1×numsi,gi-1×numsi]，和numsi。
  * 所以我们直接根据上述的情况，就能得出包含numsi的最大值fi=max(max(fi-1×numsi, gi-1×numsi), numsi)。同理，gi=min(min(fi-1*×i, gi-1×numsi), numsi)。
  * 最后由于我们要求的是最大值，直接对f数组取最大值即可。
  * */
  public int maxProduct(int[] nums) {
    int[] max = new int[nums.length];
    int[] min = new int[nums.length];

    min[0] = max[0] = nums[0];
    int result = nums[0];
    for (int i = 1; i < nums.length; i++) {
      min[i] = max[i] = nums[i];
      if (nums[i] > 0) {
        max[i] = Math.max(max[i], max[i - 1] * nums[i]);
        min[i] = Math.min(min[i], min[i - 1] * nums[i]);
      } else if (nums[i] < 0) {
        max[i] = Math.max(max[i], min[i - 1] * nums[i]);
        min[i] = Math.min(min[i], max[i - 1] * nums[i]);
      }

      result = Math.max(result, max[i]);
    }

    return result;
  }
}
