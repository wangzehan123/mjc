package archive.coinbase.vo.interleaveQuestion;

public class CircleIterator implements CustomIterator{

  public static void main(String[] args) {
    int[] arr = {1,3,5,2};
    CircleIterator circleIterator = new CircleIterator(arr, 10);
    System.out.println(circleIterator.next());
    System.out.println(circleIterator.next());
    System.out.println(circleIterator.next());
    System.out.println(circleIterator.next());
    System.out.println(circleIterator.next());
    System.out.println(circleIterator.next());
  }

  int[] arr;
  int k;
  int i = 0;

  public CircleIterator(int[] arr, int k) {
    this.arr = arr;
    this.k = k;
  }

  @Override
  public int next() {
    return arr[i++ % arr.length];
  }

  @Override
  public boolean hasNext() {
    return i < k;
  }
}
