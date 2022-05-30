package archive.flexton;

import java.util.ArrayList;

import java.util.List;


public class GoldMine {

  class Bar {

  }


  private List<Bar> bars;

  private int capacity;

  private int currentSize;


/*
	 * 1. private constructor

* 2. method synchronize getInstance -> GoldMine object
	 *
 *
3. volatile -> is in memory it
	 * **/



  public GoldMine() {

    bars = new ArrayList<>();

    this.capacity = 10;

    this.currentSize = 0;
  }

  public void put(Bar bar) {

    if(bars.size() < capacity) {

      bars.add(bar);
      currentSize ++;

    }else {
      throw new IllegalStateException();
    }
  }

  public List<Bar> get(int n){
    List<Bar> res = new ArrayList<>();
    // negative
    if(n < 0) return null;
    // n > capacity
    if(n > capacity) {
      res = bars;
      bars = new ArrayList<>();

      currentSize = 0;

      return res;
    }
    // n > bars.size()
    currentSize -= n;
    return bars.subList(0, n);


//		for(int i=0; i<n; i++) {
//			Bar b = bars.get(i);
//			currentSize--;
//			res.add(b);
//			bars.remove(i);
//		}
//
//		return res;
  }

  @Override
  public String toString() {
    return "GoldMine [bars=" + bars.toString() + ", currentSize=" + currentSize + "]";
  }
}
