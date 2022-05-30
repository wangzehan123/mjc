package archive.coinbase.vo.interleaveQuestion;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ListIterator  implements CustomIterator{

  public static void main(String[] args) {
    List<Integer> res = new ArrayList<>();
    res.add(1);
    res.add(3);
    res.add(3);
    res.add(5);
    res.add(6);
    res.add(6);
    ListIterator iterator = new ListIterator(res);
    System.out.println(iterator.next());
    System.out.println(iterator.next());
    System.out.println(iterator.next());
    System.out.println(iterator.hasNext());
    System.out.println(iterator.next());
    System.out.println(iterator.next());
    System.out.println(iterator.next());
    System.out.println(iterator.hasNext());

  }


  private final List<Integer> list;
  private int next;

  public ListIterator(List<Integer> list) {
    this.list = list;
    this.next = 0;
  }

  @Override
  public int next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    return list.get(next++);
  }

  @Override
  public boolean hasNext() {
    if (next < list.size()) {
      return true;
    }
    return false;
  }
}
