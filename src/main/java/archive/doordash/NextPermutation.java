package archive.doordash;

public class NextPermutation {

  public void nextPermutation(int[] nums) {
    int i=nums.length-2;
    while(i>=0&&nums[i]>=nums[i+1]){
      i--;
    }
    // i>=0 说明nums不是完全降序的
    if(i>=0){
      //从i往后找，找到比i大的，且最小的那个
      int j=i+1;
      while(j<nums.length&&nums[j]>nums[i]){
        j++;
      }
      j--;
      // 交换i和j
      int tmp=nums[i];
      nums[i]=nums[j];
      nums[j]=tmp;
    }
    //反转i之后的数组，让之前的从后往前的递增部分改成了从前往后递增，这样才能是下一个排列
    i=i+1;
    int j=nums.length-1;
    while(i<j){
      int tmp=nums[i];
      nums[i]=nums[j];
      nums[j]=tmp;
      i++;
      j--;
    }
  }

}
