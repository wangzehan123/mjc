package archive.microsoft.lc;

import java.util.PriorityQueue;

public class HappyString_ReverseWord {

  public String longestDiverseString(int a, int b, int c) {
    StringBuilder builder = new StringBuilder();
    PriorityQueue<Letter> maxHeap = new PriorityQueue<Letter>((x, y) -> Integer.compare(y.freq, x.freq));
    if(a > 0) maxHeap.add(new Letter('a', a));
    if(b > 0) maxHeap.add(new Letter('b', b));
    if(c > 0) maxHeap.add(new Letter('c', c));

    while(!maxHeap.isEmpty()){
      final int L = builder.length();
      Letter most = maxHeap.poll();
      char ch = most.letter;

      if(L < 2 || (builder.charAt(L - 2) != ch || builder.charAt(L - 1) != ch)){
        builder.append(ch);
        --most.freq;
      }else{
        if(maxHeap.isEmpty()) continue;
        Letter secondMost = maxHeap.poll();
        builder.append(secondMost.letter);
        --secondMost.freq;

        if(secondMost.freq > 0)
          maxHeap.add(secondMost);
      }

      if(most.freq > 0) maxHeap.add(most);
    }

    return builder.toString();
  }

  private class Letter{
    private char letter;
    private int freq;

    private Letter(char letter, int freq){
      this.letter = letter;
      this.freq = freq;
    }
  }

  /*
   * @param s: A string
   * @return: A string
   */
  public String reverseWords(String s) {
    // write your code here
    if(s.length() == 0 || s == null){
      return "";
    }
    //按照空格将s切分
    String[] array = s.split(" ");
    StringBuilder sb = new StringBuilder();
    //从后往前遍历array，在sb中插入单词
    for(int i = array.length - 1; i >= 0; i--){
      if(!array[i].equals("")) {
        if (sb.length() > 0) {
          sb.append(" ");
        }

        sb.append(array[i]);
      }
    }
    return sb.toString();
  }
}
