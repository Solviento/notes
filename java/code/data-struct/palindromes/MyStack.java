import java.util.LinkedList; // Linkedlist for popping, push, etc

public class MyStack<Any> {
  // Any ~ Shorthand for generic objects
  LinkedList<Any> list = new LinkedList<>();

  // Returns stack size
  public int size() {
    return list.size();
  }

  // Push element on stack
  public void push(Any n) {
    list.addFirst(n);
  }

  // Pop element on stack
  public Any pop() {
    return list.removeFirst();
  }

  // Retrieves element
  public Any get(int i) {
    if (isEmpty()) {
      return null;
    }
    return list.get(i);
  }

  // Important for exceptions out of bounds
  public boolean isEmpty() {
    return list.isEmpty();
  }
}