package archive.karat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class CommonAncestor {

  Map<Integer,List<Integer>> map = new HashMap<>();

  List<Integer> findparent(int[][] edges){
    if(edges==null || edges.length==0) {
      return new ArrayList<>();
    }
    for(int[] edge : edges) {
      List<Integer> list = map.get(edge[1]);
      if(list == null) {
        list = new ArrayList<>();
      }
      list.add(edge[0]);
      map.put(edge[1],list);
      list = map.get(edge[0]);
      if(list == null) {
        list = new ArrayList<>();
      }
      map.put(edge[0],list);
    }
    List<Integer> res = new ArrayList<>();
    for (Entry<Integer, List<Integer>> entry : map.entrySet()) {
      if (entry.getValue().size() == 0 || entry.getValue().size() == 1) {
        res.add(entry.getKey());
      }
    }
    return res;
  }

  private boolean hascommon(int[][] edges, int a, int b) {

    Set<Integer> a_parent= new HashSet<>();
    getall(map,a,a_parent);
    Set<Integer> b_parent = new HashSet<>();
    getall(map,b,b_parent);
    for(int n: b_parent) {
      if(a_parent.contains(n)) {
        return true;
      }
    }
    return false;
  }

  private void getall(Map<Integer,List<Integer>> map, int a, Set<Integer> parent) {
    List<Integer> list = map.get(a);
    if(list == null || list.size()==0) {
      return;
    }
    for(int p : list) {
      parent.add(p);
      getall(map,p,parent);
    }
  }



}
