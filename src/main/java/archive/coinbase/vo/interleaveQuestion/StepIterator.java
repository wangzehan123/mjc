package archive.coinbase.vo.interleaveQuestion;

import java.util.NoSuchElementException;

public class StepIterator implements CustomIterator{

  public static void main(String[] args) {
    StepIterator iterator2 = new StepIterator(8, 2, -2);
    System.out.println(iterator2.next());
    System.out.println(iterator2.next());
    System.out.println(iterator2.next());
    System.out.println(iterator2.hasNext());
  }

  private int start;
  private int stop;
  private int step;
  private int value;

  public StepIterator(int start, int end, int step) {
    this.start = start;
    this.stop = end;
    this.step = step;
    this.value = start;
  }

  @Override
  public int next() {
    if (!hasNext()) {
      throw new NoSuchElementException("Iteration exceeded.");
    }
    try {
      return value;
    } finally {
      value += step;
    }
  }

  @Override
  public boolean hasNext() {
    return step >= 0 ? value < stop : value > stop;
  }
}
