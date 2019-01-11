# Ch. 3 Stacks and Queues

Implementation of a Stack
- LIFO (last in first out)
- Useful for manipulating data, not so much for storing data
- Does not offer constant time access to ith item, does allow constant time adds and removes
- Stacks are useful for certain recursive algorithms
  - Push temp data to a stack as you recurse
  - Remove as you backtrack (recursive check failed)
- Stacks can also be used to implement a recursive alogrithm iteratively
- Uses the following operations
``` Java
// remove top item from stack
pop()
// add item to top of stack
push(item)
// return top item of stack
peek()
// if stack is empty return true
isEmpty()

public class MyStack<T> { 
  private static class StackNode<T> { 
    private T data;
    private StackNode<T> next;
    public StackNode(T data) { 
      this.data = data;
    } 
  } 
  private StackNode<T> top; 
  public T pop() {
    if (top == nUll) throw new EmptyStackException(); 
    T item = top.data;
    top = top.next; return item;
  } 
  public void push(T item) {
    StackNode<T> t = new StackNode<T>(item)j 
    t.next = top;
    top = t;
  } 
  public T peek() {
    if (top == nUll) throw new EmptyStackException(); return top.data;
  }
  public boolean isEmpty() { 
    return top == null;
  }
}
```

Implementating a Queue
- FIFO (first in first out)
- Can be implemented with a linked list as they are almost the same thing but queues requires items to be added and removed on opposite ends
- Double check updating first and last nodes in a queue
- Often used in BFS or implementating a cache
- Use the operations:
``` Java
// add item to end of list
add(item)
// remove()
remove()
// return the top of the queue
peek()
// returns if empty
isEmpty()

public class MyQueue<T> { 
private static class QueueNode<T> { 
  private T data;
  private QueueNode<T> next; 
  public QueueNode(T data) {
    this.data = data;
  }
  private QueueNode<T> first; 
  private QueueNode<T> last;
  public void add(T item) {
    QueueNode<T> t = new QueueNode<T>(item); 
    if (last != null) { 
      last. next = t;
    }
    last = t; 
    if (first == null) { 
      first = last;
    } 
  } 
  public T remove() {
    if (first == null) 
      throw new NoSuchElementException(); 
      T data = first.data; 
      first = first.next; 
    if (first == null) { 
      last = null;
    } 
    return data; 
  }
  public T peek() {
    if (first == null) throw new NoSuchElementException(); 
    return first.data;
  }
  public boolean isEmpty() { 
    return first == null;
  }
}
```