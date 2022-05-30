package archive.coinbase.vo;

/*
* iterator相关 有很多follow-up
1）先是给一个list of list，然后实现一个函数去遍历这几个lists
[[1,2],[4], [6],[],[7,8,9]] => [1, 4, 6, 7, 2, 8, 9]
2）实现一个iterator去做这个事情，需要实现next()和hasNext()
3）实现一个iterator去一个一个读第2步实现了的iterator。
[[1,2],[4], [6],[],[7,8,9]] => [1, 4, 6, 7, 2, 8, 9] 这个时候[1, 2], [4]这些是通过iterator来进行读取的
4）实现支持start, stop, step的iterator。参考Python的range函数
5）实现一个cycle 可以无限循环传入的iterator
6）实现一个带filter的iterator，这个iterator会被传入一个filter函数，这个函数返回True的话，则不需要返回数组中的值
7）实现一个函数，给一个iterator和一个filter function，返回一个正常iterator和一个reject iterator。第一个正常iterator只会返回filter之后的值。
* 第二个iterator会返回被filter掉了的值。
* */

/*
* 3. interleave iterator。给多个数组，[1,2,3],[4,5],[6]. 输出[1,4,6,2,5,3]. 以及多种followup，
* a）自己实现iterator去iterator 无限数据流，
* b） 用前面实现的iterrator， 去实现iterrator array的interleave iterator。 比如输入list<iterator>。
* step iterator. 给输入【start， step，finish】跳着iterate。比如【1，2，7】输出【1，3，5，7】。step可能是负数。
* 交叉遍历二维数组 E.g. [[1,2,3],[4,5],[6],[],[7,8,9]] =>[1,4,6,7,2,5,8,3,9]
定义iterator接口
实现一个list iterator 遍历一个list
实现range iterator
用list of iterator 作为输入，返回所有元素
实现cycle iterator
* */
import java.util.ArrayList;
import java.util.List;

public class InterleaveIterator {

  public static void main (String[] args) {
    int[][] arr = {{1,2}, {4}, {6}, {}, {7, 8, 9}};
  }
}
