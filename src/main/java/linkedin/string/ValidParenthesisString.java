package linkedin.string;

/*
* 从左边开始找，目标是要用所有的右括号把遇到的左括号都抵消掉。 遇到'('：至少需要消耗的（个数+1，definitely，最多可以消耗的（个数也要+1； 遇到''：
* （个数大于0时优先抵消一个（，同时最多可以抵消的（个数+1，以备之后如果至少需要抵消掉的（不够用时拿代替； 遇到'）'：如果最多可以抵消的（已经没有了就肯定false，
* 否则（个数大于0时抵消一个（，同时最多可以抵消的（个数也要-1；最终判断至少需要消耗掉的（个数是否为0。
* */
public class ValidParenthesisString {

  class Solution {
    public boolean checkValidString(String s) {
      char[] a = s.toCharArray();
      int n = a.length;

      int low = 0;//at least needs to complete [low] pairs
      int high = 0;//at most can complete [high] pairs

      for(int i=0; i<n; i++){
        char c = a[i];
        if(c == '('){
          low++;
          high++;
        }else if(c == '*'){
          if(low>0){ // 优先消耗一个'('
            low--;
          }
          high++; // 记录可以消耗的'('个数+1
        }else{
          if(high<=0){ // 没有任何可以消耗的'('，false
            return false;
          }else{
            if(low>0){ // 优先消耗'('，*备用
              low--;
            }
            high--; // 总共可以消耗的'('个数-1
          }
        }
      }

      return low == 0;
    }
  }
}
