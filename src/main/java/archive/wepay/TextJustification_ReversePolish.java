package archive.wepay;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TextJustification_ReversePolish {


  public int evalRPN(String[] tokens) {
    Stack<Integer> s = new Stack<Integer>();
    String operators = "+-*/";
    for (String token : tokens) {
      if (!operators.contains(token)) {
        s.push(Integer.valueOf(token));
        continue;
      }

      int a = s.pop();
      int b = s.pop();
      if (token.equals("+")) {
        s.push(b + a);
      } else if(token.equals("-")) {
        s.push(b - a);
      } else if(token.equals("*")) {
        s.push(b * a);
      } else {
        s.push(b / a);
      }
    }
    return s.pop();
  }

  public List<String> fullJustify(String[] words, int maxWidth) {
    // write your code here
    List<String> res = new ArrayList<>();
    if (words == null || words.length == 0) {
      return res;
    }

    List<String> list = new ArrayList<>();
    //len该行待填充的合法单词的总长度， 不包括空格
    int len = 0;

    for (String word: words) {
      //单词总长 + 空格数（最少保证一个空格) + 当前单词 <= maxWidth
      if (list.isEmpty() || len + list.size() + word.length() <= maxWidth) {
        list.add(word);
        len += word.length();
      }else {
        StringBuilder sb = new StringBuilder();
        int spaceCnt = maxWidth - len;
        int interval = list.size() - 1;
        //single word list;
        if (interval == 0) {
          sb.append(list.get(0));
          for (int j = 0; j < spaceCnt; ++j) {
            sb.append(" ");
          }

        }else {
          int extraSpace = spaceCnt % interval;
          int spacesEachInterval = spaceCnt / interval;

          //预处理每个interval的空格数（均摊），可以降低嵌套处理的复杂度
          String spaces = "";
          for (int k = 0; k < spacesEachInterval; ++k) {
            spaces += " ";
          }

          sb.append(list.get(0));
          for (int j = 1; j < list.size(); ++j) {
            sb.append(spaces);
            //唯一卡住的地方，题意说不清楚， 一开始以为把多余的全部塞在第一个SPACE INTERVAL,
            //其实是前面的interval每个多一个空格， 直到用完
            if (extraSpace != 0) {
              sb.append(" ");
              extraSpace--;
            }
            sb.append(list.get(j));
          }
        }

        res.add(sb.toString());
        len = word.length();
        list = new ArrayList<>();
        list.add(word);
      }
    }

    //last line
    StringBuilder sb = new StringBuilder();
    int spaceCnt = maxWidth - len;
    sb.append(list.get(0));
    for (int j = 1; j < list.size(); ++j) {
      sb.append(" ");
      sb.append(list.get(j));
    }

    int spaceLeft = maxWidth - sb.length();
    for (int k = 0; k < spaceLeft; ++k) {
      sb.append(" ");
    }
    res.add(sb.toString());

    return res;
  }
}
