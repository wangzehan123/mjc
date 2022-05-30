package archive.bolt.mianjing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule {
  public class Solution {
    /*
拓扑排序步骤：
建图并记录所有节点的入度。
将所有入度为0的节点加入队列。
取出队首的元素now，将其加入拓扑序列。
访问所有now的邻接点nxt，将nxt的入度减1，当减到0后，将nxt加入队列。
重复步骤3、4，直到队列为空。
如果拓扑序列个数等于节点数，代表该有向图无环，且存在拓扑序。
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
      List[] graph = new ArrayList[numCourses];
      int[] inDegree = new int[numCourses];

      for (int i = 0; i < numCourses; i++) {
        graph[i] = new ArrayList<Integer>();
      }

      // 建图
      for (int[] edge: prerequisites) {
        graph[edge[1]].add(edge[0]);
        inDegree[edge[0]]++;
      }

      int numChoose = 0;
      Queue queue = new LinkedList();

      // 将入度为 0 的编号加入队列
      for(int i = 0; i < inDegree.length; i++){
        if (inDegree[i] == 0) {
          queue.add(i);
        }
      }

      while (! queue.isEmpty()) {
        int nowPos = (int)queue.poll();
        numChoose++;
        // 将每条边删去，如果入度降为 0，再加入队列
        for (int i = 0; i < graph[nowPos].size(); i++) {
          int nextPos = (int)graph[nowPos].get(i);
          inDegree[nextPos]--;
          if (inDegree[nextPos] == 0) {
            queue.add(nextPos);
          }
        }
      }

      return numChoose == numCourses;
    }

    /*
     * @param numCourses: a total of n courses
     * @param prerequisites: a list of prerequisite pairs
     * @return: the course order
     */

    /*
 */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
      List[] graph = new ArrayList[numCourses];
      int[] inDegree = new int[numCourses];

      for (int i = 0; i < numCourses; i++) {
        graph[i] = new ArrayList<Integer>();
      }

      // 建图
      for (int[] edge: prerequisites) {
        graph[edge[1]].add(edge[0]);
        inDegree[edge[0]]++;
      }

      int numChoose = 0;
      Queue queue = new LinkedList();
      int[] topoOrder = new int[numCourses];

      // 将入度为 0 的编号加入队列
      for(int i = 0; i < inDegree.length; i++){
        if (inDegree[i] == 0) {
          queue.add(i);
        }
      }

      while (! queue.isEmpty()) {
        int nowPos = (int)queue.poll();
        topoOrder[numChoose] = nowPos;
        numChoose++;
        // 将每条边删去，如果入度降为 0，再加入队列
        for (int i = 0; i < graph[nowPos].size(); i++) {
          int nextPos = (int)graph[nowPos].get(i);
          inDegree[nextPos]--;
          if (inDegree[nextPos] == 0) {
            queue.add(nextPos);
          }
        }
      }

      if (numChoose == numCourses)
        return topoOrder;
      return new int[0];
    }
  }
}
