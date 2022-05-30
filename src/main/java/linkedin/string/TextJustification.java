package linkedin.string;

import java.util.ArrayList;

public class TextJustification {

  public ArrayList<String> fullJustify(String[] words, int L) {
    int wordsCount = words.length; //获取单次数量
    ArrayList<String> result = new ArrayList<String>();  //统计答案
    int curLen = 0;
    int lastI = 0;
    for (int i = 0; i <= wordsCount; i++) {
      if (i == wordsCount || curLen + words[i].length() + i - lastI > L) { //判断单行是否允许再放一个单词
        StringBuffer buf = new StringBuffer();
        int spaceCount = L - curLen;
        int spaceSlots = i - lastI - 1;
        if (spaceSlots == 0 || i == wordsCount) {
          for(int j = lastI; j < i; j++){
            buf.append(words[j]);
            if(j != i - 1)
              appendSpace(buf, 1);
          }
          appendSpace(buf, L - buf.length());
        } else {
          int spaceEach = spaceCount / spaceSlots; //如果不能，计算空格的数量和位置
          int spaceExtra = spaceCount % spaceSlots;
          for (int j = lastI; j < i; j++) {
            buf.append(words[j]);
            if (j != i - 1)
              appendSpace(buf, spaceEach + (j - lastI < spaceExtra ? 1 : 0));
          }
        }
        result.add(buf.toString());
        lastI = i;
        curLen = 0;
      }
      if (i < wordsCount)
        curLen += words[i].length();
    }
    return result;
  }

  private void appendSpace(StringBuffer sb, int count) {
    for (int i = 0; i < count; i++)
      sb.append(' ');
  }
}
