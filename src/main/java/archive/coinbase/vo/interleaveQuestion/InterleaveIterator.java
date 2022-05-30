package archive.coinbase.vo.interleaveQuestion;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class InterleaveIterator implements CustomIterator2 {

  List<Iterator> list;
  int index;

  public InterleaveIterator(List<Iterator> list) {
    this.list = list;
  }

  @Override
  public Iterator next() {
    if(!hasNext()) {
      throw new NoSuchElementException();
    }
    return list.get(index++);
  }

  @Override
  public boolean hasNext() {
    if (index >= list.size() - 1) {
      return false;
    }
    return true;
  }

}


