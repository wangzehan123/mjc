package archive.opendoor;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ExcelSumFormula {

  class Excel {
    private Map<String,String[]> map;
    private int[][]num;

    public Excel(int h, char w) {
      map=new HashMap<>();
      num=new int[h][w-'A'+1];
    }

    public void set(int r, char c, int v) {
      map.remove(r+" "+c);
      num[r-1][c-'A']=v;
    }

    public int get(int r, char c) {
      String key=r+" "+c;
      if(map.containsKey(key)) {
        return sum(r,c,map.get(key));
      }
      return num[r-1][c-'A'];
    }

    public int sum(int r, char c, String[] strs) {
      int sum=0;
      for(String s:strs) {
        int i=s.indexOf(":");
        if(i==-1)sum+=get(Integer.parseInt(s.substring(1)),s.charAt(0));
        else{//数据范围较小，A~Z，1~26
          String a=s.substring(0,i),b=s.substring(i+1);
          int h1=Integer.parseInt(a.substring(1));char w1=a.charAt(0);
          int h2=Integer.parseInt(b.substring(1));char w2=b.charAt(0);
          for(int h=h1;h<=h2;h++) {
            for(char w=w1;w<=w2;w++) {
              sum+=get(h,w);
            }
          }
        }
      }
      map.put(r+" "+c,strs);
      return sum;
    }
  }
}
