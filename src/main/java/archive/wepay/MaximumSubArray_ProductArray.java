package archive.wepay;

public class MaximumSubArray_ProductArray {

  public int maxSubArray(int[] nums) {
    int max = Integer.MIN_VALUE, sum = 0, minSum = 0;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      max = Math.max(max, sum - minSum);
      minSum = Math.min(minSum, sum);
    }

    return max;
  }

}
