package archive.dropbox;

import archive.dropbox.TextEditor.TextEditorManager;

public class Execution {


  public static void main(String[] args) {
    TextEditorManager textEditorManager = new TextEditorManager();
    TextEditor textEditor = new TextEditor(textEditorManager);
    System.out.println(textEditor.append("APPEND"));
  }
}
