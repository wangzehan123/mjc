package archive.coinbase.add;

public class MyHash {

  public static void main(String[] args) {
    MyHashMap myHashMap = new MyHashMap();
    myHashMap.put(2,4);
    System.out.println(myHashMap.get(2));
  }
  static class ListNode {
    int key, val;
    ListNode next;
    public ListNode(int key, int val, ListNode next) {
      this.key = key;
      this.val = val;
      this.next = next;
    }
  }
  static class MyHashMap {
    static final int size = 20000;
    static final int mult = 12582917;
    ListNode[] map;
    public MyHashMap() {
      this.map = new ListNode[size];
    }
    public void put(int key, int val) {
      remove(key);
      int h = hash(key);
      ListNode node = new ListNode(key, val, map[h]);
      map[h] = node;
    }
    public int get(int key) {
      int h = hash(key);
      ListNode node = map[h];
      for (; node != null; node = node.next)
        if (node.key == key) return node.val;
      return -1;
    }
    public void remove(int key) {
      int h = hash(key);
      ListNode node = map[h];
      if (node == null) return;
      if (node.key == key) map[h] = node.next;
      else for (; node.next != null; node = node.next)
        if (node.next.key == key) {
          node.next = node.next.next;
          return;
        }
    }

    private int hash(int key) {
      return (int)((long)key * mult % size);
    }
  }
}
