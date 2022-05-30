package archive.bolt.tag;

import java.util.HashMap;
import java.util.Map;

public class ReverseWords_SubarraySum {

    public String reverseWords(String s) {
      // Write your code here
      String[] words = s.split(" ");
      StringBuilder answer =new StringBuilder();
      answer.append(reverseString(words[0]));
      for (int i = 1; i < words.length; ++i) {
        answer.append(String.valueOf(' ')+reverseString(words[i]));
      }
      return answer.toString();
    }

    public String reverseString(String s) {
      String ans="";
      for(int i=s.length()-1;i>=0;i--) {
        ans+=String.valueOf(s.charAt(i));
      }
      return ans;
    }

  public class Solution {
    /**
     * 只需一次循环，Map key 为sum,
     * value为sum出现的次数。
     * 当map 中有sum - k时（即sum2 - sum1 = k. 两sum之间所有数和为 k）， 加到count。
     */
    public int subarraySumEqualsK(int[] nums, int k) {
      // write your code here
      int count = 0, sum = 0;
      Map<Integer, Integer> map = new HashMap<>();

      map.put(0, 1);

      for (int i = 0; i < nums.length; i++) {
        sum += nums[i];

        if (map.containsKey(sum - k)) {
          count = count + map.get(sum - k);
        }

        if (map.containsKey(sum)) {
          map.put(sum, map.get(sum) + 1);
        } else {
          map.put(sum, 1);
        }
      }

      return count;
    }
  }

}
