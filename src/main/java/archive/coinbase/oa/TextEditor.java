package archive.coinbase.oa;

import java.util.Stack;

class TextEditor {

  public static void main(String[] args) {
    TextEditor te = new TextEditor();
  }

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

  private StringBuilder content;
  private int cursor;
  private int selectedLength;
  private TextEditorManager textEditorManager;
  private Stack<State> undoStack;
  private Stack<State> redoStack;

  public TextEditor() {
    this.content = new StringBuilder();
    this.cursor = 0;
    this.selectedLength = 0;
    this.textEditorManager = new TextEditorManager();
    this.undoStack = new Stack<State>();
    this.redoStack = new Stack<State>();
  }

  public String append(String s) {
    //append空string时， append没有改变内容，则不要更新undostack
    if (s == null || s.isEmpty()) {
      return this.content.toString();
    }
    saveCurrentState();
    clearRedoStack();
    int start = cursor - selectedLength;
    this.content.replace(Math.max(0, start), cursor, s);
    this.cursor = start + s.length();
    this.selectedLength = 0;
    return this.content.toString();
  }

  public void move(int position) {
    this.selectedLength = 0;
    this.cursor =
        position <= 0 ? 0 : position > this.content.length() ? this.content.length() : position;
  }

  public String backspace() {
    //cursor位置在0时， backspace没有改变内容，则不要更新undostack
    if (this.cursor == 0) {
      return this.content.toString();
    }
    saveCurrentState();
    clearRedoStack();
    int start = this.cursor - this.selectedLength;
    if (this.selectedLength > 0) {
      this.content.delete(start, this.cursor);
      this.cursor = start;
    } else {
      if (this.cursor != 0) {
        this.content.deleteCharAt(this.cursor);
      }
    }
    this.selectedLength = 0;
    return this.content.toString();
  }

  public void select(int start, int end) {
    int left = Math.min(start, end);
    int right = Math.max(start, end);
    left = Math.max(0, left);
    right = Math.min(this.content.length(), right);
    this.cursor = right;
    this.selectedLength = right
        - left;
    // return this.content.substring(left, right);
  }

  public void copy() {
    this.textEditorManager.sharedClipBoard = this.content.substring(cursor - selectedLength,
        cursor);
  }

  public String paste() {
    return append(this.textEditorManager.sharedClipBoard);
  }

  public String undo() {
    this.redoStack.push(new State(this.cursor, this.selectedLength, this.content.toString()));
    if (!undoStack.isEmpty()) {
      State previous = undoStack.pop();
      this.cursor = previous.cursor;
      this.content = new StringBuilder(previous.content);
      this.selectedLength = previous.selectedLength;
    }
    return this.content.toString();
  }

  public String redo() {
    if (!redoStack.isEmpty()) {
      this.undoStack.push(new State(this.cursor, this.selectedLength, this.content.toString()));
      State redo = redoStack.pop();
      this.content = new StringBuilder(redo.content);
      this.cursor = redo.cursor;
      this.selectedLength = redo.selectedLength;
    }
    return this.content.toString();
  }

  public void saveCurrentState() {
    this.undoStack.push(new State(this.cursor, this.selectedLength, this.content.toString()));
  }

  public void clearRedoStack() {
    this.redoStack.clear();
  }
}

class TextEditorManager {

  public String sharedClipBoard;

  public TextEditorManager() {
    this.sharedClipBoard = "";
  }
}
