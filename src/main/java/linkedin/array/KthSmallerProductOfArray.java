package linkedin.array;

public class KthSmallerProductOfArray {

  public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
    long lo = (long) Integer.MIN_VALUE, hi = (long) Integer.MAX_VALUE;
    while (lo < hi) {
      long mid = lo + ((hi - lo) / 2);
      int cnt = 0;
      for (int n1 : nums1) {
        if (n1 >= 0) {
          int l = 0, r = nums2.length - 1;
          while (l <= r) {
            int mid2 = l + (r - l) / 2;
            long prod = n1 * (long) nums2[mid2];
            if (prod <= mid) {
              l = mid2 + 1;
            } else r = mid2 - 1;
          }
          cnt += l;
        } else {
          int l = 0, r = nums2.length - 1, p = 0;
          while (l <= r) {
            int mid2 = l + (r - l) / 2;
            long prod = n1 * (long) nums2[mid2];
            if (prod <= mid) {
              p = nums2.length - mid2;
              r = mid2 - 1;
            } else l = mid2 + 1;
          }
          cnt += p;
        }
      }
      if (cnt >= k) {
        hi = mid;
      } else lo = mid + 1L;
    }
    return lo;
  }
}
