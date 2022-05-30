package archive.coinbase.vo.interleaveQuestion;

import java.util.ArrayList;
import java.util.List;

public class Interleave {

    public static List<Integer> Interleave(int[][] arr) {
        List<Integer> list = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i].length, max);
        }

        int k = 0;
        while (k <= max) {
            for (int i = 0; i < arr.length; i++) {
                if (k >= arr[i].length) {
                    continue;
                }
                list.add(arr[i][k]);
            }
            k++;
        }
        return list;
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3}, {}, {5, 6}, {},{7, 8, 9}};
        List<Integer> res = Interleave(arr);
        System.out.println(res);
    }

}
