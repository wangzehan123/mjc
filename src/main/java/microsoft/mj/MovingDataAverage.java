package microsoft.mj;

import java.util.LinkedList;
import java.util.Queue;

public class MovingDataAverage {

  public class MovingAverage {
    private double previousSum = 0.0;
    private int maxSize;
    private Queue<Integer> currentWindow;

    public MovingAverage(int size) {
      currentWindow = new LinkedList<Integer>();
      maxSize = size;
    }

    public double next(int val) {
      if (currentWindow.size() == maxSize)
      {
        previousSum -= currentWindow.remove();
      }

      previousSum += val;
      currentWindow.add(val);
      return previousSum / currentWindow.size();
    }}
}
