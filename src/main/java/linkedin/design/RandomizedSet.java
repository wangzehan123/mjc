package linkedin.design;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
public class RandomizedSet {

  ArrayList<Integer> nums;
  HashMap<Integer, Integer> num2index;
  Random rand;

  public RandomizedSet() {
    // do initialize if necessary
    nums = new ArrayList<Integer>();
    num2index = new HashMap<Integer, Integer>();
    rand = new Random();
  }

  // Inserts a value to the set
  // Returns true if the set did not already contain the specified element or false
  public boolean insert(int val) {
    if (num2index.containsKey(val)) {
      return false;
    }

    num2index.put(val, nums.size());
    nums.add(val);
    return true;
  }

  // Removes a value from the set
  // Return true if the set contained the specified element or false
  public boolean remove(int val) {
    if (!num2index.containsKey(val)) {
      return false;
    }

    int index = num2index.get(val);
    if (index < nums.size() - 1) { // not the last one than swap the last one with this val
      int last = nums.get(nums.size() - 1);
      nums.set(index, last);
      num2index.put(last, index);
    }
    num2index.remove(val);
    nums.remove(nums.size() - 1);
    return true;
  }

  // Get a random element from the set
  public int getRandom() {
    return nums.get(rand.nextInt(nums.size()));
  }
}

