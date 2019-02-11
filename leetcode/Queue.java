public class Queue {
  private int back;
  private int front;
  private int size;
  private int[] queueArray;

  public MyQueue(int size) {
    this.back = 0;
    this.front = 0;
    this.size = size;
    this.queueArray = new int[size];
  }

  public void enqueue(int data) {
    queueArray[back++] = data;
    if (back % size == 0)
      back = 0;
  }

  public int dequeue() {
    int toRet;
    if (front == back)
      return -1;
    toRet = queueArray[front++];
    if (front % size == 0)
      front = 0;
    return toRet;
  }

  public boolean isEmpty() {
    if (front == back)
      return true;
    return false;
  }

  public String toString() {
    String toRet = "";
    for (int i = front; i != back;) {
      toRet += queueArray[i] + " -> ";
      i++;
      if (i % size == 0)
        i = 0;
    }
    return toRet;
  }

}