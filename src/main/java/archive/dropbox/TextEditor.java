package archive.dropbox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;


public class TextEditor {

  class State {

    int cursor;
    int selectedLength;
    String content;

    public State(int cursor, int selectedLength, String content) {
      this.cursor = cursor;
      this.selectedLength = selectedLength;
      this.content = content;
    }
  }

  static class TextEditorManager {

    public String sharedClipBoard;
    Map<String, TextEditor> textEditorMap;
    Stack<TextEditor> activeTextEditorStack;

    public TextEditorManager() {
      this.sharedClipBoard = "";
      this.textEditorMap = new HashMap<>();
      this.activeTextEditorStack = new Stack<>();
    }

    public void open(String name) {
      TextEditor textEditor = textEditorMap.getOrDefault(name, new TextEditor(this));
      textEditorMap.putIfAbsent(name, textEditor);
      activeTextEditorStack.push(textEditor);
    }

    public void close() {
      TextEditor textEditor = activeTextEditorStack.pop();
      textEditor.close();
    }

    public TextEditor getCurrentActiveTextEditor() {
      return activeTextEditorStack.peek();
    }
  }

  private TextEditorManager textEditorManager;
  private StringBuilder content;
  private int cursor;
  private int selectedLength;
  private Stack<State> previousStateStack;
  private Stack<State> undoStack;

  public TextEditor(TextEditorManager textEditorManager) {
    // Empty content when the editor is opened.
    this.content = new StringBuilder();
    this.cursor = 0;
    this.selectedLength = 0;
    previousStateStack = new Stack<>();
    undoStack = new Stack<>();
    this.textEditorManager = textEditorManager;
  }

  public String append(String input) {
    saveCurrentState();
    clearUndoStack();
    int start = cursor - selectedLength;
    content.replace(start, cursor, input);
    selectedLength = 0;
    return content.toString();
  }

  public String delete() {
    saveCurrentState();
    clearUndoStack();
    int start = cursor - selectedLength;
    if (start == cursor) {
      content.deleteCharAt(start);
    } else {
      content.delete(start, cursor);
    }
    cursor = start;
    selectedLength = 0;
    return content.toString();
  }

  public void move(int position) {
    // Single cursor move clears the selected length.
    selectedLength = 0;
    if (position <= 0) {
      cursor = 0;
      return;
    }
    if (position >= content.length()) {
      cursor = content.length();
      return;
    }
    cursor = position;
  }

  public String select(int selectPointer1, int selectPointer2) {
    int lowerBound = Math.min(selectPointer1, selectPointer2);
    int upperBound = Math.max(selectPointer1, selectPointer2);
    lowerBound = Math.max(0, lowerBound);
    upperBound = Math.min(content.length(), upperBound);
    // Set cursor to the upper bound.
    cursor = upperBound;
    selectedLength = upperBound - lowerBound;
    return content.substring(lowerBound, upperBound);
  }

  public String copy() {
    textEditorManager.sharedClipBoard = content.substring(cursor - selectedLength, cursor);
    return textEditorManager.sharedClipBoard;
  }

  public String paste() {
    return append(textEditorManager.sharedClipBoard);
  }

  public String undo() {
    // Push the current state to the undo stack.
    State currentState = new State(cursor, selectedLength, content.toString());
    undoStack.push(currentState);
    if (!previousStateStack.isEmpty()) {
      State previousState = previousStateStack.pop();
      content = new StringBuilder(previousState.content);
      cursor = previousState.cursor;
      selectedLength = previousState.selectedLength;
    } else {
      content = new StringBuilder();
      cursor = 0;
      selectedLength = 0;
    }
    return content.toString();
  }

  public String redo() {
    if (undoStack.isEmpty()) {
      throw new RuntimeException("Cannot Redo!");
    }
    previousStateStack.push(new State(cursor, selectedLength, content.toString()));
    State beforeUndo = undoStack.pop();
    content = new StringBuilder(beforeUndo.content);
    cursor = beforeUndo.cursor;
    selectedLength = beforeUndo.selectedLength;
    return content.toString();
  }

  public void close() {
    selectedLength = 0;
    cursor = content.length();
    previousStateStack.clear();
    undoStack.clear();
  }

  private void saveCurrentState() {
    previousStateStack.push(new State(cursor, selectedLength, content.toString()));
  }

  private void clearUndoStack() {
    undoStack.clear();
  }

  public static void main(String[] args) {
    TextEditorManager textEditorManager = new TextEditorManager();
    TextEditor textEditor = new TextEditor(textEditorManager);
    String[][] arr = {
        {"APPEND","Never give up"},
        {"MOVE","-10"},
        {"APPEND","START."},
        {"MOVE","20"},
        {"APPEND","END."},
        {"DELETE"}
    };
    List<String> list = new ArrayList<>();
    for (String[] s : arr) {
      String expression = s[0];
      switch (expression) {
        case "APPEND":
          textEditor.append(s[1]);
          list.add(textEditor.content.toString());
          continue;
        case "MOVE":
          textEditor.move(Integer.parseInt(s[1]));
          list.add(textEditor.content.toString());
          continue;
        case "DELETE":
          textEditor.delete();
          list.add(textEditor.content.toString());
          continue;
        case "select":
          int left = Integer.parseInt(s[1]);
          int right = Integer.parseInt(s[2]);
          textEditor.select(left, right);
          list.add(textEditor.content.toString());
          continue;
        case "copy":
          textEditor.copy();
          list.add(textEditor.content.toString());
          continue;
        case "paste":
          textEditor.content.append(textEditor.paste());
          list.add(textEditor.content.toString());
          continue;
      }
    }
    System.out.println(list);
  }

}
