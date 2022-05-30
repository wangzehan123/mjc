package archive.oscar;

class MyCircularQueue {
  private int[] data;
  private int size,front=-1,rear=-1;
  private int DEFAULT_SIZE=0;
  public MyCircularQueue(int k) {
    size=0;
    DEFAULT_SIZE=k;
    data = new int[k];
  }

  public boolean enQueue(int value) {
    if(isFull()) return false;
    if(isEmpty()){
      //we need to increament both at first insertion
      front++;
      rear++;
    }else{
      rear = (rear+1)%DEFAULT_SIZE;
    }
    data[rear] = value;
    size++;
    return true;
  }

  public boolean deQueue() {
    if(isEmpty()){
      return false;
    }
    front = (front+1)%DEFAULT_SIZE;
    size--;
    return true;
  }

  public int Front() {
    if(isEmpty()) return -1;
    return data[front];
  }

  public int Rear() {
    if(isEmpty()) return -1;
    return data[rear];
  }

  public boolean isEmpty() {
    if(size==0){
      //whenever queue is empty start the front and rear from the start
      front=-1;
      rear=-1;
      return true;
    }
    return false;
  }

  public boolean isFull() {
    if(size==DEFAULT_SIZE) return true;
    return false;
  }
}
