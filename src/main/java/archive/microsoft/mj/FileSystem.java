package archive.microsoft.mj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
focus 在error handling和clear error message。
mkdir on parent dir not exist，
mkdir on dir already exist
readfile against dir
readfile not exist
writefile on parent dir not exist
writefile on created dir
middle directory traverse code 需要检查每一层都是dire
last node in the path 需要检查： 对于mkdir， child不存在；
对于write_file，child不是directory或者不存在；
对于read_file， child存在并且是个file。
 */
public class FileSystem {

  public static void main(String[] args) throws Exception {
    String filePath = "/a/b/c";
    String contentPath = "/a/b/c/d";
    String readPath = "/a/b/c/d";
    FileSystem fileSystem = new FileSystem();
    fileSystem.mkdir(filePath);
//    fileSystem.mkdir("/a/b");
    fileSystem.addContentToFile(contentPath, "Hello");
    System.out.println(fileSystem.readContentFromFile(readPath));
  }

  Node root;

  public FileSystem() {
    root = new Node("/");
  }

  public List<String> ls(String path) throws Exception {
    Node node = traverse(path);
    List<String> res = new ArrayList<>();
    if (node.isFile) {
      res.add(node.name);
    }else {
      for (String child : node.children.keySet()) {
        res.add(child);
      }
    }
    return res;
  }

  public void mkdir(String path) throws Exception {
    Node node = traverse(path);
    if (!node.children.isEmpty()) {
      throw new Exception("Directory already exists!");
    }
  }

  public void addContentToFile(String filePath, String content) throws Exception {
    Node node = traverse(filePath);
    node.isFile = true;
    node.content = content;
  }

  public String readContentFromFile(String filePath) throws Exception {
    Node node = traverse(filePath);
    if (!node.isFile) {
      throw new Exception("this is not a file");
    }
    return node.content;
  }

  private Node traverse(String filePath) throws Exception {
    String[] path = filePath.split("/");
    Node curr = root;
    for (int i = 1; i < path.length; i++) {
      if (!curr.children.containsKey(path[i])) {
        Node node = new Node(path[i]);
        curr.children.put(path[i], node);
      }
      curr = curr.children.get(path[i]);
      if (curr.isFile && i != path.length - 1) {
        throw new Exception("This is a file not a directory path");
      }
    }
    return curr;
  }

  class Node {
    String name;
    boolean isFile;
    String content;
    HashMap<String, Node> children;

    public Node (String name) {
      this.name = name;
      isFile = false;
      content = "";
      children = new HashMap<>();
    }
  }

}
