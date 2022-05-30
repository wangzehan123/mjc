package archive.microsoft.mj;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class InsertDeleteGetRandom {
  ArrayList<Integer> nums;
  HashMap<Integer, Integer> locs;
  java.util.Random rand = new java.util.Random();
  /** Initialize your data structure here. */

  public InsertDeleteGetRandom() {
    nums = new ArrayList<Integer>();
    locs = new HashMap<Integer, Integer>();
  }

  /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
  public boolean insert(int val) {
    boolean contain = locs.containsKey(val);
    if ( contain ) return false;
    locs.put( val, nums.size());
    nums.add(val);
    return true;
  }

  /** Removes a value from the set. Returns true if the set contained the specified element. */
  public boolean remove(int val) {
    boolean contain = locs.containsKey(val);
    if ( ! contain ) return false;
    int loc = locs.get(val);
    if (loc < nums.size() - 1 ) { // not the last one than swap the last one with this val
      int lastone = nums.get(nums.size() - 1 );
      nums.set( loc , lastone );
      locs.put(lastone, loc);
    }
    locs.remove(val);
    nums.remove(nums.size() - 1);
    return true;
  }

  /** Get a random element from the set. */
  public int getRandom() {
    return nums.get( rand.nextInt(nums.size()) );
  }

  /**
   * Using ArrayList & HashMap.
   *
   * Time Complexity: All function have average O(1)
   *
   * Space Complexity: O(N)
   *
   * N = Number of values currently stored in the data structure.
   */
  class RandomizedCollection {

    List<Integer> nums;
    Map<Integer, Set<Integer>> idxMap;
    Random random;

    public RandomizedCollection() {
      nums = new ArrayList<>();
      idxMap = new HashMap<>();
      random = new Random();
    }

    public boolean insert(int val) {
      boolean response = !idxMap.containsKey(val);

      if (response) {
        idxMap.put(val, new HashSet<>());
      }
      idxMap.get(val).add(nums.size());
      nums.add(val);

      return response;
    }

    public boolean remove(int val) {
      if (!idxMap.containsKey(val)) {
        return false;
      }

      Set<Integer> idxSet = idxMap.get(val);
      int idxToBeRemoved = idxSet.iterator().next();
      if (idxSet.size() == 1) {
        idxMap.remove(val);
      } else {
        idxSet.remove(idxToBeRemoved);
      }

      int lastIdx = nums.size() - 1;
      if (idxToBeRemoved != lastIdx) {
        int lastVal = nums.get(lastIdx);
        Set<Integer> lastIdxSet = idxMap.get(lastVal);
        lastIdxSet.add(idxToBeRemoved);
        lastIdxSet.remove(lastIdx);
        nums.set(idxToBeRemoved, lastVal);
      }

      nums.remove(lastIdx);

      return true;
    }

    public int getRandom() {
      return nums.get(random.nextInt(nums.size()));
    }
  }
}
