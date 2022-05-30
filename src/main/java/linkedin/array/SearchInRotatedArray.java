package linkedin.array;

public class SearchInRotatedArray {

  public class Solution {
    public int search(int[] A, int target) {
      if (A == null || A.length == 0) {
        return -1;
      }

      int start = 0;
      int end = A.length - 1;
      int mid;

      while (start + 1 < end) {
        mid = start + (end - start) / 2;
        if (A[mid] == target) {
          return mid;
        }
        if (A[start] < A[mid]) {
          // situation 1, red line
          if (A[start] <= target && target <= A[mid]) {
            end = mid;
          } else {
            start = mid;
          }
        } else {
          // situation 2, green line
          if (A[mid] <= target && target <= A[end]) {
            start = mid;
          } else {
            end = mid;
          }
        }
      } // while

      if (A[start] == target) {
        return start;
      }
      if (A[end] == target) {
        return end;
      }
      return -1;
    }
  }

  public class SolutionTwo{
    /**
     * @param A: an integer ratated sorted array and duplicates are allowed
     * @param target: An integer
     * @return: a boolean
     */
    public boolean search(int[] A, int target) {
      if (A.length == 0){
        return false;
      }
      // step1: find pivot
      int left = 0, right = A.length - 1;
      while (left < right){
        int mid = left + (right - left) / 2;
        if (A[mid] > A[right]){
          left = mid + 1;
        }
        else if(A[mid] < A[right]){
          right = mid;
        }
        else{
          if (right > 0 && A[right - 1] > A[right]){
            left = right;
          }
          else{
            right -= 1;
          }
        }
      }
      int pivot = left;

      // step2: split
      if (pivot == 0){
        left = 0;
        right = A.length - 1;
      }
      else if (target >= A[0]){
        left = 0;
        right = pivot - 1;
      }
      else{
        left = pivot;
        right = A.length - 1;
      }

      // step3: find target
      while (left + 1 < right) {
        int mid = left + (right - left) / 2;
        if (A[mid] < target) {
          left = mid;
        } else {
          right = mid;
        }
      }

      if (A[left] == target) {
        return true;
      }
      if (A[right] == target) {
        return true;
      }
      return false;
    }
  }
}
